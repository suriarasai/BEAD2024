import pyarrow.csv as pv
import pyarrow.parquet as pq

# Read csf file into dataframe
people_table = pv.read_csv('people.csv')
# write dataframe into a parquet file
pq.write_table(people_table, 'people1.parquet')
# read parquet file into dataframe
parquet_file = pq.ParquetFile('people1.parquet')
# print parquet file metadata
print(parquet_file.metadata)
print(parquet_file.metadata.row_group(0))

# read hdb resale price
hdb_table = pv.read_csv("resale-flat-prices-based-on-registration-date-from-mar-2012-to-dec-2014.csv")
# convert the CSV file to a Parquet file
pq.write_table(hdb_table,'resale-flat-prices-based-on-registration-date-from-mar-2012-to-dec-2014.parquet')
hdb_parquet = pq.ParquetFile('resale-flat-prices-based-on-registration-date-from-mar-2012-to-dec-2014.parquet')
# inspect the parquet metadata
print(hdb_parquet.metadata)
# inspect the parquet row group metadata
print(hdb_parquet.metadata.row_group(0))
# inspect the column chunk metadata
print(hdb_parquet.metadata.row_group(0).column(9).statistics)
