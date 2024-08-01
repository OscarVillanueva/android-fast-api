from fastapi import FastAPI, Depends
from sqlalchemy.ext.asyncio import AsyncSession
from api.src.models.CreateUserModel import CreateUserModel
from api.src.db.config.connection import get_db

from api.src.db.models.UserDTO import UserDTO

app = FastAPI()

@app.get('/')
def root():
    return "Hello World"

@app.get("/create-user")
async def root(user: CreateUserModel, db: AsyncSession = Depends(get_db)):

    userDTO = UserDTO()
    userDTO.username = user.username
    userDTO.password = user.password

    db.add(userDTO)
    await db.commit()
    await db.refresh(userDTO)
    return userDTO
