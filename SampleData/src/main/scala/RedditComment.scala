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
    RedditComment(arr(0), arr(1), arr(2), arr(3), arr(4), arr(5), arr(6), arr(7), arr(8), arr(9), arr(10), arr(11), arr(12),
      arr(13), arr(14), arr(15), arr(16), arr(17), arr(18), arr(19), arr(20), arr(21), arr(22))
  }
}
