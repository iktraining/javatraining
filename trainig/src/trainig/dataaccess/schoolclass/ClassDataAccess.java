package trainig.dataaccess.schoolclass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import trainig.DBConnection;
import trainig.model.schoolclass.ClassCode;
import trainig.model.schoolclass.ClassName;

public class ClassDataAccess {

	public ClassDataAccess() {}

//クラス名オブジェクトからOptionalでラップされたクラスコードオブジェクトを返す
//クラスコードが取得できなかった場合も同上。
	public Optional<ClassCode> getCode(ClassName className) {

		String code = null;
		String sql = "select class_code from classes where class_name = ?";
		boolean isEmpty = true;

		try (PreparedStatement pstmt =  DBConnection.getConnection().prepareStatement(sql);){
			pstmt.setString(1, className.getName());
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()){
				code = rset.getString(1);
				isEmpty = false;
			}
			if(isEmpty) {
				return Optional.ofNullable(null);
			}
			return Optional.ofNullable(new ClassCode(code));
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

