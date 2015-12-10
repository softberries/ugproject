case class RedditComment(
                          id: String,
                          created_utc: String,
                          ups: String,
                          subreddit_id: String,
                          link_id: String,
                          name: String,
                          score_hidden: String,
                          author_flair_css_class: String,
                          author_flair_text: String,
                          subreddit: String,
                          id2: String,
                          removal_reason: String,
                          gilded: String,
                          downs: String,
                          archived: String,
                          author: String,
                          score: String,
                          retrieved_on: String,
                          body: String,
                          distinguished: String,
                          edited: String,
                          controversiality: String,
                          parent_id: String
                        )


object RedditComment {
  def apply(arr: Array[String]): RedditComment = {
    RedditComment(arr(0), "etc...", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
  }
}
