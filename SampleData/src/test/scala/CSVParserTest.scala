import java.io.File
import java.net.URL

import org.scalatest.{Matchers, FlatSpec}


class CSVParserTest extends FlatSpec with Matchers {

  "CSVParser" should "parse example csv and return proper number of results" in {
    //given
    val parser = new CSVParser()
    //when
    val csvUrl: URL = getClass.getResource("/sample_data_1000_records.csv")
    val result = parser.parseFile(new File(csvUrl.toURI))
    //then
    result.size should be === 1000
  }

}
