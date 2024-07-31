from fastapi import FastAPI

app = FastAPI()

@app.get("/api")
async def root():
    return {"message": "Hello World"}

@app.get("/api/test")
async def root():
    return {"message": "Hello Test"}

@app.get("/api/python")
async def root():
    return {"message": "Hello Python - 2"}

@app.get("/api/docker")
async def root():
    return {"message": "Hello docker"}