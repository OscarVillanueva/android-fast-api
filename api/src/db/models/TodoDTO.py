from sqlalchemy import Column, Integer, Text, Boolean
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class TodoDTO(Base):
  __tablename__ = 'todods'
  id = Column(Integer, primary_key=True, index=True)
  todo = Column(Text)
  is_completed = Column(Boolean)
  belong_to = Column(Integer)