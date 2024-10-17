
package br.com.DAO;

import br.com.DTO.AgendaDTO;
import br.com.Views.TelaAgenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class AgendaDAO {
    public static AgendaDAO objAgendaDAO;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    public void inserirAgenda(AgendaDTO objAgendaDTO) {
        String sql = "INSERT INTO tb_agenda(id, cliente, data, hora, descricao) VALUES(?, ?, ?, ?, ?)";

        conexao = new ConexaoDAO().conector();

        try {
            // Verifica se o ID do usuário é menor que 0
            if (objAgendaDTO.getId() < 0) {
                JOptionPane.showMessageDialog(null, "Erro: ID da agenda não pode ser menor que 0.");
                return;
            }

            // Verifica se os campos obrigatórios estão preenchidos
            if (objAgendaDTO.getCliente().isEmpty()
                    || objAgendaDTO.getData().isEmpty()
                    || objAgendaDTO.getHora().isEmpty()
                    || objAgendaDTO.getDescricao().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Erro: Todos os campos são obrigatórios.");
                return;
            }

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objAgendaDTO.getId());
            pst.setString(2, objAgendaDTO.getCliente());
               pst.setString(3, objAgendaDTO.getData());
            pst.setString(4, objAgendaDTO.getHora());
            pst.setString(5, objAgendaDTO.getDescricao());

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Agenda inserida com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Agenda já existe ou não foi inserido.");
            }

            pst.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de erro para entrada duplicada
                JOptionPane.showMessageDialog(null, "Erro: Agenda já existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir agenda: " + e.getMessage());
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
    public void editar(AgendaDTO objAgendaDTO) {

        String sql = "update tb_agenda set cliente=?, data=?, hora=?, descricao=?  where id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(5, objAgendaDTO.getId());
            pst.setString(1, objAgendaDTO.getCliente());
            pst.setString(2, objAgendaDTO.getData());
            pst.setString(3, objAgendaDTO.getHora());
             pst.setString(4, objAgendaDTO.getDescricao());
               

            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Agenda Editada com sucesso!");
                apagarCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Metodo editar" + e);
            
        }
        
    }
    public void apagarCampos() {

        TelaAgenda.txtId.setText(null);
             TelaAgenda.txtCliente.setText(null);
        TelaAgenda.txtData.setText(null);
        TelaAgenda.txtHora.setText(null);
        TelaAgenda.txtDescricao.setText(null);
             
    }
    public void apagar(AgendaDTO objAgendaDTO){
    String sql = " delete from tb_agenda where id = ?";
    conexao = ConexaoDAO.conector();
    
    try{
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objAgendaDTO.getId());
        int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Agenda Excluida com sucesso!");
                apagarCampos();
            }
        
        
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Metodo apagar "+ e);
    }
}
}
