
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Datos-Masivos/Unidad_1/spark_dataframes/Sales.csv")

df.printSchema()

df.show()
// Other Aggregate Functions
//a
//practica 5
// 20 ejemplos
/////1
df.groupBy("Company").mean().show()     //Saca la media de cada compa;ia
/////2
df.groupBy("Company").count().show()        //Nos devuelve los valores no nulos que contiene cada grupo de la columna company
/////3
df.groupBy("Company").max().show()          // Nos devuelve el valor maximo que contiene cada grupo de la columna Company
/////4
df.groupBy("Company").min().show()          //Nos devuelve el valor minimo que contiene cada grupo de la comlumna Company
/////5
df.groupBy("Company").sum().show()      //Nos devuelve la suma total de cada grupo que contiene la columna Company
/////6
df.groupBy($"Sales").avg(10).show       //Calcule el valor medio de cada columna numérica para cada grupo.
/////7
val companias=df.groupBy($"Company").agg(collect_set($"Person").as ("personas")).show()      //Calcule los agregados especificando una serie de columnas de agregados y Calcule los agregados especificando una serie de columnas de agregados.
////collect_set (): devuelve valores distintos para una clave particular especificada en el método collect_set (field)
/////8
df.select($"Sales".name("Ventas")).show()       //Asigna un nombre a la columna
/////9
df.select(avg("Sales")).show()      //Calcule el valor medio de cada columna numérica para cada grupo.
/////10
df.groupBy(df.col("Person")).agg(avg("Sales")).show()       //que calcula la media y el valor maximo de subconjunto de datos agrupados por Person
/////11
val Sales= df.filter($"Sales" < 300).show()         //devuelve todas las ventas que son mayores a 300 por medio de filtro
/////12
df.head(3)      //Nos devuelve los tres primeros elementos de la collecion.
/////13
df.groupBy($"Company").agg(max($"Sales")).show()        //Nos muestra la compa;ia y su venta maxima que hicieron cada una.
/////14
val companias=df.groupBy($"Company").agg(concat_ws(",", collect_set($"Person").as ("personas"))).show
//////////devuelve valores distintos para una clave particular especificada en el método collect_set (field) y quitamos corchetes.
/////15
df.groupBy($"Person").sum("Sales").withColumnRenamed("sum(Sales)","ventas").sort(desc("ventas")).limit(4).show()
/////////Nos devuelve la columna person con las ventas maximas y renombramos la columna Sales por ventas
/////16
df.groupBy(col("Sales"),col("Company"),col("Person")).count().show  //agrupacion de varias columnas y  Contamos el número de filas para cada grupo.
/////17
df.filter(col("Sales")<=600).groupBy().count().show     //filtramos en la columna Sales las que son menores a 600 y contamos cuantas filas son
/////18
df.groupBy($"Sales").agg(count("*")).show()     //Cuente el número de filas para cada grupo.
/////19
df.groupBy($"Sales").count().withColumnRenamed("Sales","SALES").filter($"SALES" >=200).show
///////////Devuele la columna sales renombrada y filtra el contenido de esa columna con una condicion la cual especifica que si SALES es mayor o igual que docientos nos la muestre
/////20
val frequentcompany=df.stat.freqItems(Seq("Company")).show()    //accederemos al   método freqItems para encontrar los  elementos frecuentes  en la columna de marco de datos company.
/////21
val sales=df.filter($"Sales">=300 && $"Company">="GOOG").stat.crosstab("Sales","Company").show(10) ///accederemos al   método de tabla cruzada para mostrar una vista tabular de la puntuación por Sales
/////22
df.stat.approxQuantile("Sales", Array(0,0.5,1), 0.25)
///////El primer parámetro del método approxQuantile () es la columna de su marco de datos en el que se ejecutan las estadísticas, el segundo parámetro es una matriz de probabilidades cuantiles y el tercer parámetro es un factor de error de precisión .
