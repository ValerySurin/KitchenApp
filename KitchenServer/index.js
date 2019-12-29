var express = require('express');
var app = express();
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());


const sql = require('mysql2');
const connection = sql.createConnection({
  host: '45.84.1.115',
  user: 'kitchen_admin',
  database: 'KitchenApp',
  password: "1"
}).promise();

app.get('/mainpage', function(req, res) {
  connection.query('SELECT id_recipe, id_user, time_of_cook, rate_of_recipe, nickname_of_user, recipe_photo FROM recipe inner join user on user.id_of_user = recipe.id_user', function(err, results, fields) {
     var mainPageRecipes = results;
       res.json(mainPageRecipes);
    })
});

app.post('/search',function(req, res) {
  var requestProduct = req.body;
  var listOfProduct=[];
  var sqlStringForSearch = "select id_recipe from recipe_structure where id_products in (1, 4) group by id_recipe having count(id_recipe) = 2 "
  for (var i = 0; i<requestProduct.id.length; i++){
    listOfProduct.push(requestProduct.id[i]);
  }
//   for (var i = 0; i<listOfProduct.length; i++){
//     if(i!=0){
//     sqlStringForSearch += " and "
//   }
//     sqlStringForSearch += " id_products = "
//     sqlStringForSearch += listOfProduct[i]
//
//   }
// console.log(sqlStringForSearch)
  // connection.query(`select distinct recipe.id_recipe from recipe inner join recipe_structure on recipe.id_recipe=recipe_structure.id_recipe where id_products in (?)`, [listOfProduct]
  connection.query( "select id_recipe from recipe_structure where id_products in (?) group by id_recipe having count(id_recipe) = ? ", [listOfProduct, listOfProduct.length],
).then(data=>{
  var listOfRequiredRecipe=[];
  for (var i = 0; i<data[0].length; i++){
    listOfRequiredRecipe.push(data[0][i].id_recipe);
  }
  connection.query('SELECT id_recipe, id_user, time_of_cook, rate_of_recipe, recipe_photo, nickname_of_user FROM recipe inner join user on user.id_of_user = recipe.id_user where id_recipe in(?)', [listOfRequiredRecipe]).then(data1=>{
       res.json(data1[0]);
    })
})
});

// app.get('/recipefull', function(req, res) {
//   var recipeID = req.body;
//   var count = [];
//   count.push(recipeID.id);
//   var sqlString = "select nickname_of_user, recipe_photo, time_of_cook, rate_of_recipe, id_products, ingredient_count, type_of_count, product_name, color_product, content_of_step, number_of_step from user inner join recipe on user.id_of_user=recipe.id_user inner join recipe_structure on recipe.id_recipe=recipe_structure.id_recipe inner join products on recipe_structure.id_products = products.id_product inner join step_selection on step_selection.id_recipe = recipe.id_recipe inner join step on step_selection.id_step = step.id_step where recipe.id_recipe=" + count[0];
//   connection.query(sqlString, function(err, results, fields) {
//      var returnRecipe = results;
//        res.json(returnRecipe);
//     })
// });


app.post('/recipefull', function(req, res) {
  var recipeID = req.body;
  var count = [];
  count.push(recipeID.id);

  var obj = {
  id: count[0],
  productName: [],
  productColor: [],
  productCount: [],
  productType: [],
  steps:[],
  stepNumber: []
}

  var sqlString = "select id_products, ingredient_count, type_of_count, name_of_product, color_of_product from user inner join recipe on user.id_of_user=recipe.id_user inner join recipe_structure on recipe.id_recipe=recipe_structure.id_recipe inner join products on recipe_structure.id_products = products.id_of_product where recipe.id_recipe=" + count[0];
  connection.query(sqlString).then(result=>{
    for(var i = 0; i<result[0].length; i++){
      obj.productName.push(result[0][i].name_of_product);
      obj.productColor.push(result[0][i].color_of_product);
      obj.productCount.push(result[0][i].ingredient_count);
      obj.productType.push(result[0][i].type_of_count);
    }

  // var sqlString2 = "select content_of_step, number_of_step from recipe inner join step_selection on step_selection.id_recipe = recipe.id_recipe inner join step on step.id_step = step_selection.id_step where recipe.id_recipe =" + count[0];

      var sqlString2 = "select content_of_step, number_of_step from recipe inner join step on step.step_id_recipe = recipe.id_recipe where recipe.id_recipe =" + count[0];
      connection.query(sqlString2).then(result2=>{
        for(var i = 0; i<result2[0].length; i++){
          obj.steps.push(result2[0][i].content_of_step);
          obj.stepNumber.push(result2[0][i].number_of_step);
        }
          console.log(obj);
           res.json(obj);
        })
  });
});

app.post('/addrecipe', function(req, res) {
var o = req.body;
console.log(o);
var IdRecipe
 connection.query("insert into recipe(id_user, time_of_cook, rate_of_recipe, recipe_photo) values(?, ?, 0, ?)", [o.id, o.time, o.photo],).then(result3=>{
   var sqlString3 = "insert into recipe_structure(id_products, id_recipe, ingredient_count, type_of_count) values "
   IdRecipe = result3[0].insertId
   for(var i = 0; i<o.productCount.length; i++){
     if (i!=o.productCount.length-1){
     var l = " ( " + o.product[i] + ", " + result3[0].insertId + ", " + o.productCount[i] + ", " + o.productType[i] + "),"
   }
   else{
     var l = " ( " + o.product[i] + ", " + result3[0].insertId + ", " + o.productCount[i] + ", " + o.productType[i] + ")"
   }
     sqlString3 += l
   }
   connection.query(sqlString3).then(result4 =>{
     var sqlString4 = "insert into step(content_of_step, number_of_step, step_id_recipe) values"
     for(var i = 0; i<o.stepContent.length; i++){
       if (i!=o.stepContent.length-1){
       var l = " ( '" + o.stepContent[i] + "', " + o.stepNumber[i] + ", " + IdRecipe + "),"
     }
     else{
       var l = " ( '" + o.stepContent[i] + "', " + o.stepNumber[i] + ", " + IdRecipe + ")"
     }
       sqlString4 += l
     }

     connection.query(sqlString4).then(result5 =>{

       console.log(result5);
     })
   })

 })



       res.json("ok");
});



 app.listen(3333);
