
package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import br.com.Views.TelaPrincipal;
import br.com.Views.TelaUsuario;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class UsuarioDAO {

    public static UsuarioDAO objUsuarioDAO;

Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar(UsuarioDTO objusuarioDTO) {
        String sql = "select * from tb_usuarios1 where usuario = ? and senha = ?";
        conexao = ConexaoDAO.conector();

        try {
            // preparar a consulta no banco, em função ao que foi inserido nas caixas de texto
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objusuarioDTO.getLogin_usuario());
            pst.setString(2, objusuarioDTO.getSenha_usuario());

//            executa a query
            rs = pst.executeQuery();
//            verifica se existe usuario
            if (rs.next()) {
                // obtem o conteúdo do atributo perfil
                String perfil = rs.getString(5);
//                System.out.println(perfil);

                //tratamento de perfil
                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.subMenuUsuario.setEnabled(true);
                    conexao.close();//Fechar a conexão                    
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    conexao.close();//Fechar a conexão   

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalidos");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "* Metodo Logar **" + e);
        }
        
    }
    
    // INSERIR
    
    public void inserirUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "INSERT INTO tb_usuarios1(id_usuario, usuario, email, login, senha) VALUES(?, ?, ?, ?, ?)";

        conexao = new ConexaoDAO().conector();

        try {
            // Verifica se o ID do usuário é menor que 0
            if (objUsuarioDTO.getId_usuario() < 0) {
                JOptionPane.showMessageDialog(null, "Erro: ID do usuário não pode ser menor que 0.");
                return;
            }

            // Verifica se os campos obrigatórios estão preenchidos
                   if (objUsuarioDTO.getNome_usuario().isEmpty()
                   || objUsuarioDTO.getEmail_usuario().isEmpty()
                    || objUsuarioDTO.getLogin_usuario().isEmpty()
                    || objUsuarioDTO.getSenha_usuario().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Erro: Todos os campos são obrigatórios.");
                return;
            }

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            pst.setString(2, objUsuarioDTO.getNome_usuario());
            pst.setString(3, objUsuarioDTO.getEmail_usuario());
            pst.setString(4, objUsuarioDTO.getLogin_usuario());
            pst.setString(5, objUsuarioDTO.getSenha_usuario());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Usuário já existe ou não foi inserido.");
            }

            pst.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de erro para entrada duplicada
                JOptionPane.showMessageDialog(null, "Erro: Usuário já existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir usuário: " + e.getMessage());
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
    
    // EDITAR 
    
    public void editar(UsuarioDTO objUsuarioDTO) {

        String sql = "update tb_usuarios1 set usuario=?,email=?,  login=?, senha=? where id_usuario = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(5, objUsuarioDTO.getId_usuario());
            pst.setString(1, objUsuarioDTO.getNome_usuario());
            pst.setString(2, objUsuarioDTO.getEmail_usuario());
            pst.setString(3, objUsuarioDTO.getLogin_usuario());
            pst.setString(4, objUsuarioDTO.getSenha_usuario());

            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuario Editado com sucesso!");
                apagarCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Metodo editar" + e);
            
        }
    }
   
    public void apagarCampos() {

        TelaUsuario.txtId.setText(null);
        TelaUsuario.txtUsuario.setText(null);
        TelaUsuario.txtEmail.setText(null);
        TelaUsuario.txtLogin.setText(null);
        TelaUsuario.txtSenha.setText(null);
    }
    
    // APAGAR
    
    
    public void apagar(UsuarioDTO objUsuarioDTO){
    String sql = " delete from tb_usuarios1 where id_usuario = ?";
    conexao = ConexaoDAO.conector();
    
    try{
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objUsuarioDTO.getId_usuario());
        int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuario Excluido com sucesso!");
                apagarCampos();
            }
        
        
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Metodo apagar "+ e);
    }
}
    
      }
