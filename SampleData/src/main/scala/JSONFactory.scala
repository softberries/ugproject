/*import play.api.libs.json.JsArray
import play.api.libs.json.Json*/
import play.api.libs.json._

class JSONFactory {

  def createJSONObject(redditObjects: List[RedditComment]): JsValue = {
    Json.toJson(
      redditObjects.map { r =>
        Map("id" -> r.id, "created_utc" -> r.created_utc, "ups" -> r.ups, "subreddit_id" -> r.subreddit_id,
          "link_id" -> r.link_id, "name" -> r.name, "score_hidden" -> r.score_hidden,
          "author_flair_css_class" -> r.author_flair_css_class, "author_flair_text" -> r.author_flair_text,
          "subreddit" -> r.subreddit, "id2" -> r.id2, "removal_reason" -> r.removal_reason, "gilded" -> r.gilded,
          "downs" -> r.downs, "archived" -> r.archived, "author" -> r.author, "score" -> r.score,
          "retrieved_on" -> r.retrieved_on,"body" -> r.body, "distinguished" -> r.distinguished,
          "edited" -> r.edited, "controversiality" -> r.controversiality, "parent_id" -> r.parent_id)
      }
    )
  }

}
