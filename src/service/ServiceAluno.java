package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import entity.Aluno;
import persistence.AlunoDao;

@Path("/aluno")
public class ServiceAluno {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String gravar(String aluno) {
		try {
			Aluno a = new Gson().fromJson(aluno, Aluno.class);
			a.gerarMedia().gerarSituacao();
			new AlunoDao().create(a);
			return new Gson().toJson(a);
		}catch(Exception ex) {
			return "Error: " + ex.getMessage();
		}
	}
	
	@GET
	public String listar(){
		try {
			return new Gson().toJson(new AlunoDao().findAll());
		}catch(Exception ex) {
			return null;
		}
	}
	
	@Path("/{idAluno}")
	@GET
	public String buscarId(@PathParam("idAluno") String idAluno) {
		try {
			return new Gson().toJson(new AlunoDao().findById(new Integer (idAluno)));
		}catch(Exception ex) {
			return "Error: " + ex.getMessage();
		}
	}
	
	
}
