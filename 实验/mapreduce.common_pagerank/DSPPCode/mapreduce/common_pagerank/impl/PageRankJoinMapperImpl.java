package DSPPCode.mapreduce.common_pagerank.impl;

import DSPPCode.mapreduce.common_pagerank.question.PageRankJoinMapper;
import DSPPCode.mapreduce.common_pagerank.question.utils.ReduceJoinWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageRankJoinMapperImpl extends PageRankJoinMapper {

  @Override
  public void map(LongWritable key, Text value,
      Mapper<LongWritable, Text, Text, ReduceJoinWritable>.Context context)
      throws IOException, InterruptedException {
    String line = value.toString();
    String[] segments = line.split(" ", 2);
    ReduceJoinWritable val = new ReduceJoinWritable();
    val.setData(segments[1]);
    Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
    Matcher isNum = pattern.matcher(segments[1]);
    if(isNum.matches()) val.setTag("2");
    else val.setTag("1");
    context.write(new Text(segments[0]), val);
  }
}
