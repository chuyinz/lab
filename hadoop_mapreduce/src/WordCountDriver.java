import java.io.*;
import javax.servlet.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
public class WordCountDriver {


    public static void main(String[] args)  throws Exception{
        // TODO Auto-generated method stub

        Configuration conf = new Configuration();
        Job job = new Job(conf, "Word count");
        job.setJarByClass(WordCountDriver.class);
        //job.setJobName("Max Temprature");  !replaced by new job(...


        //setup the MepRed
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);


        //setup the output format
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //setup the output format
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //指定源文件夹和输出文件夹
        FileInputFormat.setInputPaths(job, new Path("/wordcount/dataset/"));
        FileOutputFormat.setOutputPath(job, new Path("/wordcount/output/"));
        //exit when job finished
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}

