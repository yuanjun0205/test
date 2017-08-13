package cn.cloudbae.gson.demo7;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest7 {
	public static void main(String[] args) {
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("张三");
		s1.setBirthDay(new Date());
		
		Student s2 = new Student();
		s2.setId(2);
		s2.setName("李四");
		s2.setBirthDay(new Date());
		
		Student s3 = new Student();
		s3.setId(3);
		s3.setName("王五");
		s3.setBirthDay(new Date());
		
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(s1);
		stuList.add(s2);
		stuList.add(s3);
		
		Teacher t = new Teacher();
		t.setId(8);
		t.setName("江素英");
		t.setTitle("班主任");
		
		CClass cclass = new CClass();
		cclass.setClassNo("6");
//		cclass.setClassName("进取班");
		cclass.setStudents(stuList);
		cclass.setTeacher(t);
		
		Teacher t1 = new Teacher();
		t1.setId(1);
		t1.setName("教师1");
		t1.setTitle("数学老师");
		Teacher t2 = new Teacher();
		t2.setId(2);
		t2.setName("教师2");
		t2.setTitle("物理教师");
		
		Map<String,Teacher> otherTeachers = new HashMap<String,Teacher>();
		otherTeachers.put("教师1", t1);
		otherTeachers.put("教师2", t2);
		cclass.setOtherTeachers(otherTeachers);
		
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.enableComplexMapKeySerialization()
	            .serializeNulls()
	            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
	            .setDateFormat("yyyy-MM-dd HH:mm:ss")//时间转化为特定格式 
				.create();
		
		//将对象转换成JSON串
		String s = gson.toJson(cclass);
		System.out.println(s);
		
		//将JSON串转换成Java对象
		CClass c = gson.fromJson(s, CClass.class);
		System.out.println(c.getOtherTeachers().keySet());
		
		
	}
}
