package com.test.spark;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaRDD<String> textFile = sc.textFile("hdfs://...");
		JavaRDD<String> words = textFile.flatMap(new FlatMapFunction<String, String>() {
		  public Iterable<String> call(String s) { return Arrays.asList(s.split(" ")); }
		});
		JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
		  public Tuple2<String, Integer> call(String s) { return new Tuple2<String, Integer>(s, 1); }
		});
		JavaPairRDD<String, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
		  public Integer call(Integer a, Integer b) { return a + b; }
		});
		counts.saveAsTextFile("hdfs://...");
	}

}
