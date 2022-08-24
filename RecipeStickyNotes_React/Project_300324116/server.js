const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const MongoClient = require('mongodb').MongoClient;
const ObjectId = require('mongodb').ObjectID;

const app = express();
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

const url = "mongodb://localhost:27017";
//any name
app.get("/api/recipes", (req,res)=>{
    MongoClient.connect(url, (err,db)=>{
        if(err) throw err;

        //Database Name
        const dbo = db.db("projectDB");

        //Collection name in the DB
        dbo.collection("recipeInfo").find({}).toArray((error,result)=>{
            
            if(error){
                res.send("Error is " + error);
                return;
            }
            else{
                res.send(JSON.stringify(result));
                db.close();
            }
        });

    });
});

//when we need to something to the database we use post.
app.post('/api/recipes', (req, res) => {
    const name = req.body.name;
    const ingridents = req.body.ingridents;

    //console.log(name, ingridents);
    const myObj = { name: name, ingridents: ingridents};

    MongoClient.connect(url, (err, db) => {
        if (err) throw err;

        const dbo = db.db("projectDB");
        dbo.collection("recipeInfo").insertOne(myObj, (error, result) => {
            if (error) {
                res.send("Error", error);
                return;
            }
            res.send(result);
            db.close();
        });
    });
});

app.delete('/api/recipes/:id', (req, res) => {
    const id = req.params.id;
    const objectId = ObjectId(id);
    console.log(id, objectId);
    const myObj = {_id: objectId};

    MongoClient.connect(url, (err, db) => {
        if (err) throw err;

        const dbo = db.db("projectDB");
        dbo.collection("recipeInfo").deleteOne(myObj, (error, result) => {
            if (error) {
                res.send("Error", error);
                return;
            }
            if(result.deletedCount === 1)
            res.send("Record deleted successfully");
            db.close();
        });
    });
});


app.get("/api/favorites", (req,res)=>{
    MongoClient.connect(url, (err,db)=>{
        if(err) throw err;

        //Database Name
        const dbo = db.db("projectDB");

        //Collection name in the DB
        dbo.collection("favoriteInfo").find({}).toArray((error,result)=>{
            
            if(error){
                res.send("Error is " + error);
                return;
            }
            else{
                res.send(JSON.stringify(result));
                db.close();
            }
        });

    });
});

app.post('/api/favorites', (req, res) => {
    const title = req.body.title;
    const ingridents = req.body.ingridents;
    const image = req.body.image;

    //console.log(name, ingridents);
    const myObj = { title: title, ingridents: ingridents, image:image};

    MongoClient.connect(url, (err, db) => {
        if (err) throw err;

        const dbo = db.db("projectDB");
        dbo.collection("favoriteInfo").insertOne(myObj, (error, result) => {
            if (error) {
                res.send("Error", error);
                return;
            }
            res.send(result);
            db.close();
        });
    });
});

app.delete('/api/favorites/:id', (req, res) => {
    const id = req.params.id;
    const objectId = ObjectId(id);
    console.log(id, objectId);
    const myObj = {_id: objectId};

    MongoClient.connect(url, (err, db) => {
        if (err) throw err;

        const dbo = db.db("projectDB");
        dbo.collection("favoriteInfo").deleteOne(myObj, (error, result) => {
            if (error) {
                res.send("Error", error);
                return;
            }
            if(result.deletedCount === 1)
            res.send("Record deleted successfully");
            db.close();
        });
    });
});

app.listen(5000,()=>{
    console.log("Server is runnning on port 5000.");
});