/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.DTO.ClientesDTO;
import br.com.DTO.UsuarioDTO;
import br.com.Views.TelaClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.saolucas
 */
public class ClientesDAO {

    public static ClientesDAO objClientesDAO;


    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void inserirClientes(ClientesDTO objClientesDTO) {
        String sql = "INSERT INTO tb_cliente(id_cliente, usuario, endereco, telefone, email, cpf_cnpj) VALUES(?, ?, ?, ?, ?, ?)";

        conexao = new ConexaoDAO().conector();

        try {
            // Verifica se o ID do usuário é menor que 0
            if (objClientesDTO.getId_usuario() < 0) {
                JOptionPane.showMessageDialog(null, "Erro: ID do usuário não pode ser menor que 0.");
                return;
            }

            // Verifica se os campos obrigatórios estão preenchidos
                   if (objClientesDTO.getNome_clientes().isEmpty()
                   || objClientesDTO.getEndereco().isEmpty()
                    || objClientesDTO.getTelefone().isEmpty()
                    || objClientesDTO.getEmail_clientes().isEmpty()
                    || objClientesDTO.getCpf_cnpj().isEmpty()){
                JOptionPane.showMessageDialog(null, "Erro: Todos os campos são obrigatórios.");
                return;
            }

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objClientesDTO.getId_usuario());
            pst.setString(2,  objClientesDTO.getNome_clientes());
            pst.setString(3, objClientesDTO.getEndereco());
            pst.setString(4, objClientesDTO.getTelefone());
            pst.setString(5, objClientesDTO.getEmail_clientes());
            pst.setString(6, objClientesDTO.getCpf_cnpj());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Cliente já existe ou não foi inserido.");
            }

            pst.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de erro para entrada duplicada
                JOptionPane.showMessageDialog(null, "Erro: Cliente já existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir Cliente: " + e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
      public void editar(ClientesDTO objClientesDTO) {

        String sql = "update tb_cliente set usuario=?, endereco=?, telefone=?, email=?, cpf_cnpj=? where id_cliente = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(6, objClientesDTO.getId_usuario());
            pst.setString(1, objClientesDTO.getNome_clientes());
            pst.setString(2, objClientesDTO.getEndereco());
            pst.setString(3, objClientesDTO.getTelefone());
             pst.setString(4, objClientesDTO.getEmail_clientes());
              pst.setString(5, objClientesDTO.getCpf_cnpj());

            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Cliente Editado com sucesso!");
                apagarCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Metodo editar" + e);
            
        }
      
    }
        public void apagarCampos() {

        TelaClientes.txtId.setText(null);
             TelaClientes.txtUsuario.setText(null);
        TelaClientes.txtEndereco.setText(null);
        TelaClientes.txtTelefone.setText(null);
        TelaClientes.txtEmail.setText(null);
             TelaClientes.txtCpfCnpj.setText(null);
    }
        
        /////
        
        public void apagar(ClientesDTO objClientesDTO){
    String sql = " delete from tb_cliente where id_cliente = ?";
    conexao = ConexaoDAO.conector();
    
    try{
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objClientesDTO.getId_usuario());
        int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso!");
                apagarCampos();
            }
        
        
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Metodo apagar "+ e);
    }
}
    }

