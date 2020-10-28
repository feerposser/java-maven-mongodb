package br.com.rest;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dao.PersonagemDAO;
import br.com.db.DB;
import br.com.model.Personagem;

@Path("/personagem")
public class PersonagemREST {

	PersonagemDAO pDAO = null;

	public PersonagemREST() {
		this.pDAO = new PersonagemDAO();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonagens() throws SQLException {
		return Response.ok(this.pDAO.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonagem(@PathParam("id") String id) {
		return Response.ok(pDAO.findById(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPersonagem(Personagem personagem) {
		try {
			boolean result = pDAO.insert(personagem);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(personagem).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(personagem).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizaPersonagem(Personagem personagem) {
		try {
			boolean result = pDAO.update(personagem);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(personagem).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(personagem).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaPersonagem(@PathParam("id") String id) {
		try {
			boolean result = pDAO.delete(pDAO.findById(id));
			if (result) {
				return Response.status(Response.Status.OK).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
