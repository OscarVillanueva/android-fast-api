import bcrypt
import jwt
import os
from typing import Annotated
from datetime import datetime, timedelta, timezone
from dotenv import load_dotenv
from fastapi import FastAPI, Depends, Header
from sqlalchemy.ext.asyncio import AsyncSession
from api.src.models.CreateUserModel import CreateUserModel
from api.src.models.CreateTodoParam import CreateTodoParam
from api.src.db.config.connection import get_db
from api.src.db.models.UserDTO import UserDTO
from api.src.db.models.TodoDTO import TodoDTO
from sqlalchemy.future import select

print("stating")
load_dotenv()

app = FastAPI()

@app.get('/')
def root():
    return "Hello World"

@app.get("/create-user")
async def root(user: CreateUserModel, db: AsyncSession = Depends(get_db)):
    try: 
        userDTO = UserDTO()
        userDTO.username = user.username
        pwd = bcrypt.hashpw(user.password.encode("utf-8"), bcrypt.gensalt())
        userDTO.password = pwd.decode("utf-8")

        db.add(userDTO)
        await db.commit()
        await db.refresh(userDTO)
        return {
            "id": userDTO.id,
            "username": userDTO.username,
        }
    except Exception as error:
        return {
            "message": "An error occurred while creating the user",
            "description": str(error)
        }
    
@app.get("/login")
async def root(user: CreateUserModel, db: AsyncSession = Depends(get_db)):
    try:
        result = await db.execute(select(UserDTO).where(UserDTO.username == user.username))
        
        dbUser = result.scalar_one_or_none()

        if (dbUser == None):
            return {
                "message": "Username or password incorrect"
            }
        
        hashed_password = dbUser.password.encode('utf-8')

        if bcrypt.checkpw(user.password.encode('utf-8'), hashed_password): 

            secret = os.getenv("JWT_SECRET")
            token = jwt.encode(
                {
                    "exp": datetime.now(tz=timezone.utc) + timedelta(days = 1),
                    "id": dbUser.id,
                    "username": dbUser.username,
                }, 
                secret, 
                algorithm="HS256"
            )

            return {
                "jwt": token,
            }
        else:
            return {
                "message": "Username or password incorrect"
            }

    except Exception as error:
        return {
            "message": "An error occurred while logging in",
            "description": str(error)
        }

@app.post('/todo')
async def root(todo: CreateTodoParam,authorization: Annotated[str, Header()], db: AsyncSession = Depends(get_db)):
    if("Bearer" not in authorization):
        return {
            "message": "Missing authorization header"
        }

    try:
        secret = os.getenv("JWT_SECRET")
        token = jwt.decode(authorization.replace("Bearer ", ""), secret, algorithms=["HS256"]) 

        if not token["id"]:
            raise Exception("Invalid token")
        
        todoDTO = TodoDTO()
        todoDTO.todo = todo.todo
        todoDTO.belong_to = token["id"]
        todoDTO.is_completed = False

        db.add(todoDTO)
        await db.commit()
        await db.refresh(todoDTO)

        return { "id": todoDTO.id, "todo": todoDTO.todo}
    except Exception as error:
        print(error)
        return { 
            "message": "An error occurred while creating a todo",
            "description": str(error)
        }    
