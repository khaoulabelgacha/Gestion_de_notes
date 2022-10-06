package util;

import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.EtudiantDao;
import dao.ExamDao;
import dao.ModuleDao;
import dao.NoteDao;
import entities.Exam;
import entities.Note;

public class Test {

	public static void main(String[] args) {
		try {
//			 ExamDao n = new ExamDao( Database.connectionFactory().getCnx());
			NoteDao p=new NoteDao( Database.connectionFactory().getCnx());
			ExamDao a=new ExamDao( Database.connectionFactory().getCnx());
			ModuleDao m=new ModuleDao( Database.connectionFactory().getCnx());
			Exam ex= new Exam();
			
			List<Note> l=null;
			try {
				l = p.list(3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0; i< l.size(); i++){
			    System.out.println(l.get(i).getExam().getId());
				System.out.println(l.get(i).getNote());
				
				
				System.out.println("--------------");
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
