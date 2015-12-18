library(RSQLite)
library(dplyr)
library(rjson)

run <- function(){
  # remove scientific notation
  options(scipen=999)
  setwd("~/Downloads/")
  reddit_db <- src_sqlite('~/Downloads/database.sqlite', create = FALSE)
  for(i in 0:7){
    skip_rows <- i * 100000
    sql_query <- paste("SELECT * FROM May2015 LIMIT 100000,", skip_rows)
    cat(sql_query)
    cat("\n")
    db_subset <- tbl(reddit_db, sql(sql_query))
    if (!exists("dataset")){
      dataset <- data.frame(db_subset)
    }else {
      temp_dataset <- data.frame(db_subset)
      dataset<- merge(dataset, temp_dataset, all.x = TRUE, all.y = TRUE)
      rm(temp_dataset)
    }
    cat("\n")
    cat("nrow for final dataset: ")
    cat(nrow(dataset))
    cat("\n")
    write.csv(dataset, paste0(paste0("sampledata",i),".csv"))
    rm(dataset)
  }
  
}