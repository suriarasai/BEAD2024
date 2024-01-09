package movie

import java.awt.Font

import org.jfree.chart.axis.CategoryLabelPositions

import scalax.chart.module.ChartFactories

object UserOccupationChart {

  def main(args: Array[String]) {
    val userDataFrame = MovieUtil.getUserFieldDataFrame()
    val occupation = userDataFrame.select("occupation")
    val occupation_groups = userDataFrame.groupBy("occupation").count()
    val occupation_groups_sorted = occupation_groups.sort("count")
    occupation_groups_sorted.show()
    val occupation_groups_collection = occupation_groups_sorted.collect()

    val ds = new org.jfree.data.category.DefaultCategoryDataset
    val mx = scala.collection.immutable.ListMap()

    for( x <- 0 until occupation_groups_collection.length) {
      val occ = occupation_groups_collection(x)(0)
      val count = Integer.parseInt(occupation_groups_collection(x)(1).toString)
      ds.addValue(count,"UserAges", occ.toString)
    }

    val chart = ChartFactories.BarChart(ds)
    val font = new Font("Dialog", Font.PLAIN,5);

    chart.peer.getCategoryPlot.getDomainAxis().
      setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    chart.peer.getCategoryPlot.getDomainAxis.setLabelFont(font)
    chart.show()
    MovieUtil.sc.stop()
  }
}
