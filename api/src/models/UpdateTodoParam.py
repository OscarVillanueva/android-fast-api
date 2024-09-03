from pydantic import BaseModel

class UpdateTodoParam(BaseModel): 
  status: bool