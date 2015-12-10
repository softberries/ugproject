import org.scalatest.{Matchers, FlatSpec}
import play.api.libs.json.{JsArray, JsValue}

class JSONFactorySpec extends FlatSpec with Matchers {

  "JSONFactory" should "create a JSON array object from a list of Reddit objects" in {
    //given
    val jsonFactory = new JSONFactory()
    val redditList = List(createRedditComment(), createRedditComment(), createRedditComment())
    //when
    val jsonObj: JsValue = jsonFactory.createJSONObject(redditList)
    //then
    //jsonObj.value.size should be === 3
  }

  def createRedditComment(): RedditComment = new RedditComment("","","","","","","","","","","","","","","","","","","","","","","")
}
