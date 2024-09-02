from pydantic import BaseModel

class CreateTodoParam(BaseModel): 
  todo: str