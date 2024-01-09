package movie

import java.awt.Font

import org.jfree.chart.axis.CategoryLabelPositions

import scalax.chart.module.ChartFactories

object MovieAgesChart {

  def main(args: Array[String]) {

    val movie_years_count_collect_sort = MovieData.getMovieYearsCountSorted()

    val ds = new org.jfree.data.category.DefaultCategoryDataset
    for(i <- movie_years_count_collect_sort){
      ds.addValue(i._2.toDouble,"year", i._1)
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
