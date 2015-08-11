/**
 * Created by FScoward on 15/08/11.
 */
object Main extends App {
    
  val x1: String = "abc"
  val x2: String = ""
  val x3: Option[String] = Some("abc")
  val x4: Option[String] = Some("")
  val x5: Option[String] = Some("12345")
  val x6: Option[String] = Some("not a number")

  val errors = new scala.collection.mutable.HashMap[String, String]()
  
  def putErrors(f: => Boolean, key: String, value: String) = {
    if(f) {
      errors.put(key, value)
    }
  }
  
  putErrors(x1.isEmpty, "x1", "error.required")
  putErrors(x2.isEmpty, "x2", "error.required")

  println(errors)
  /*
  if (x1.isEmpty) {
    errors.put("x1", "error.required")
  }
  if (x2.isEmpty) {
    errors.put("x2", "error.required")
  }
  */
  putErrors((x3.isDefined && !x3.get.isEmpty), "x3", "error.required")
  putErrors((x4.isDefined && !x4.get.isEmpty), "x4", "error.required")
  
  /*
  if (x3.isDefined && !x3.get.isEmpty) {
    errors.put("x3", "error.required")
  }
  if (x4.isDefined && !x4.get.isEmpty) {
    errors.put("x4", "error.required")
  }
  */
  
  def getToInt(key: String, value: String, f: => Boolean, g: Option[String]): Unit = {
    if(f) {
      try{
        g.get.toInt
      } catch {
        case e: Exception => errors.put(key, value)
      }
    }
  }
  
  getToInt("x5", "error.number", (x5.isDefined && !x5.get.isEmpty), x5)
  
  if (x5.isDefined && !x5.get.isEmpty) {
    try {
      x5.get.toInt
    } catch {
      case e: Exception =>
        errors.put("x5", "error.number")
    }
  }
  if (x6.isDefined && !x6.get.isEmpty) {
    try {
      x6.get.toInt
    } catch {
      case e: Exception =>
        errors.put("x6", "error.number")
    }
  }

}
