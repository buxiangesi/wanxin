package course.model;

public class Course {
	
	private int id = -1;
	private String name;
	private String teacher;
	
	//ѡ��ģ���¼�Ƿ���ѡ��("0"����δѡ��1������ѡ)
	private String selected;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}	
}
