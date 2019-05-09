package dataaccess.schoolclass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dataaccess.dbconnection.DBConnection;
import model.schoolclass.ClassCode;
import model.schoolclass.ClassName;
import model.schoolclass.SchoolClass;
import model.schoolclass.SchoolClassList;

public class ClassDataAccess {

	public ClassDataAccess() {}

	//クラス名オブジェクトからOptionalでラップされたクラスコードオブジェクトを返す
	//クラスコードが取得できなかった場合、nullのOptionalを返す
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
	//クラスコードオブジェクトからOptionalでラップされたクラス名オブジェクトを返す
	//クラス名が取得できなかった場合、nullのOptionalを返す
		public Optional<ClassName> getName(ClassCode classCode) {

			String name = null;
			String sql = "select class_name from classes where class_code = ?";
			boolean isEmpty = true;

			try (PreparedStatement pstmt =  DBConnection.getConnection().prepareStatement(sql);){
				pstmt.setString(1, classCode.getCode());
				ResultSet rset = pstmt.executeQuery();
				while(rset.next()){
					name = rset.getString(1);
					isEmpty = false;
				}
				if(isEmpty) {
					return Optional.ofNullable(null);
				}
				return Optional.ofNullable(new ClassName(name));
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	//DBからすべてのSchoolClassを取得します。
	//DBのSchool ClassはNOT NULL制約があるため、このメソッドではNULL値を考慮していません
		public SchoolClassList getList() {
			ArrayList<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();

			String sql = "select class_code, class_name from classes";

			try (PreparedStatement pstmt =  DBConnection.getConnection().prepareStatement(sql);){
				ResultSet rset = pstmt.executeQuery();
				while(rset.next()){
					String code = rset.getString("class_code");
					String name = rset.getString("class_name");
					schoolClasses.add(new SchoolClass(new ClassCode(code), new ClassName(name)));
				}
				return new SchoolClassList(schoolClasses);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
