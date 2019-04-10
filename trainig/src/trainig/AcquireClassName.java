package trainig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainig.model.schoolclass.ClassName;
import trainig.model.student.StudentNo;

public class AcquireClassName {
	private ClassName className;

	public AcquireClassName() {}

	public void acquireName(StudentNo studentNo) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "select class_name from students st inner join classes cl on st.class_code = cl.class_code where st.student_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentNo.getNo());
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()){
				String name = rset.getString("class_name");
				className = new ClassName(name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ClassName getClassName() {
		return className;
	}

	public boolean hasClassName() {
		if(className == null) {
			return false;
		}
		return true;
	}
}
