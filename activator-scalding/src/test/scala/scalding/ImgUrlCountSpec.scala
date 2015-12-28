package scalding

import org.scalatest.FunSpec
import scalding.TestData._

class ImgUrlCountSpec extends FunSpec {

  describe("ImgUrlCount") {

    it("creates empty output for empty input") {
      val output = "output/imgurl-count-empty.txt"
      val emptyFile = tempFile("imageurl-count")
      com.twitter.scalding.Tool.main(Array(
        "ImgUrlCount", "--local", "--input", emptyFile.getAbsolutePath(), "--output", output))
      assert (io.Source.fromFile(output).getLines.size === 0)
    }

    it("creates tab-delimited url/count pairs, one per line for non-empty input") {
      val output = "output/diggJsonText.txt"
      val file = writeTempFile("imgurl-count", activatorText)
      com.twitter.scalding.Tool.main(Array(
        "ImgUrlCount", "--local", "--input", file.getAbsolutePath(), "--output", output))

      val expected = sampleJsonUrlCountExpected.map{ case (url, count) => s"$url\t$count" }
      assert (expected.size == 3)
    }
  }
}