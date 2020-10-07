package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Aluno;

public class AlunoDao extends Dao{

	public void create(Aluno a) throws Exception{
		if(a.getNota1()<0 || a.getNota1()>10) {
			throw new IllegalArgumentException("Nota 1 Inválida!");
		}
		if(a.getNota2()<0 || a.getNota2()>10) {
			throw new IllegalArgumentException("Nota 2 Inválida!");
		}
		open();
			stmt = con.prepareStatement("insert into aluno values(null,?,?,?,?,?,?)");
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getDisciplina());
			stmt.setDouble(3, a.getNota1());
			stmt.setDouble(4, a.getNota2());
			stmt.setDouble(5, a.getMedia());
			stmt.setString(6, a.getSituacao());
			stmt.execute();
			stmt.close();
		close();
	}
	
	public List<Aluno> findAll() throws Exception{
		open();
			stmt = con.prepareStatement("select * from aluno");
			rs = stmt.executeQuery();
			List<Aluno> lista = new ArrayList<Aluno>();
			while(rs.next()) {				
				Aluno a = new Aluno(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
				a.setMedia(rs.getDouble("media"));
				a.setSituacao(rs.getString("situacao"));
				lista.add(a);
			}		
		close();
		return lista;
	}
	
	public Aluno findById(Integer id) throws Exception{
		Aluno resp = findAll().stream().filter(x -> x.getIdAluno().equals(id)).findAny().orElse(null);
		return resp;
	}
	
	public static void main(String[] args) {
		try {
		AlunoDao dao = new AlunoDao();
		Aluno a1 = new Aluno(null,"Edson Belem","Java WebDeveloper",5.,7.).gerarMedia().gerarSituacao();		
		dao.create(a1);
		}catch(Exception ex) {
			
		}
	}
	
}
