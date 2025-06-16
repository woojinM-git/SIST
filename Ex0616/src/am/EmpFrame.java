/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package am;

import am.vo.EmpVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 100
 */
public class EmpFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EmpFrame.class.getName());

    String[][] data;
    String[] c_name = {"사번", "이름", "입사일", "급여", "부서명"};

    SqlSessionFactory factory;
    List<EmpVO> list;
    int i;

    public EmpFrame() {
        initComponents(); // 화면 구성

        init(); // DB연결
        allData(); // 적용된 data를 보여줌

        // 이벤트 감지자 등록
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // 프로그램 종료
            }
        });

        // ----------------------------------- 날짜를 입력하고 버튼을 눌렀을 때 실행-------------------
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 검색 버튼을 누를 때 수행함!
                String start = jTextField1.getText().trim();
                String end = jTextField2.getText().trim();
                if(start.length() > 0 && end.length() > 0){
                    // mapper(emp.xml)에 search_date를 호출하기 위해
                    // 지정된 파라미터 객체(parameterType)인 Map을 생성
                    Map<String, String> map = new HashMap<>(); // Map 생성
                    map.put("start", start);
                    map.put("end", end);

                    SqlSession ss = factory.openSession();
                    list = ss.selectList("emp.search_date", map); // 호출하면서 map 전달
                    ss.close();
                    viewTable(list);
                }else {
                    JOptionPane.showMessageDialog(EmpFrame.this, "날짜를 모두 입력하세요");
                }
            }
        });
        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 테이블에서 더블클릭을 했는지 확인
                int cnt = e.getClickCount();
                if(cnt == 2){
                    // JTable에 선택된 행, index를 얻어내자
                    i = jTable1.getSelectedRow();
//                    setTitle(String.valueOf(i));
                    // 위의 i는 List<EmpVO>에 접근하기 위한 index다.
                    EmpVO vo = list.get(i);
//                    setTitle(vo.getEname());
                    new MyDialog(EmpFrame.this,false, vo);
                }
            }
        });
    } // 생성자 끝

    private void init() {
        try {
            Reader r = Resources.getResourceAsReader("am/config/conf.xml");

            factory = new SqlSessionFactoryBuilder().build(r);
            r.close();
            this.setTitle("준비완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void allData() {
        SqlSession ss = factory.openSession();
        list = ss.selectList("emp.all");
        viewTable(list);
        ss.close();
    }

    private void viewTable(List<EmpVO> list) {
        // 받은 list를 2차원 배열로 변환한 후 JTable에 표현
        data = new String[list.size()][c_name.length]; // 첫번째자리는 컬럼들의 수만큼,
        int i = 0;
        for(EmpVO vo : list){
            data[i][0] = vo.getEmpno(); // 사번
            data[i][1] = vo.getEname(); // 이름
            data[i][2] = vo.getHiredate(); // 입사일
            data[i][3] = vo.getSal(); // 급여
            data[i][4] = vo.getDname(); // 부서명
            i++;
        }
        jTable1.setModel(new DefaultTableModel(data, c_name){
            @Override
            public boolean isCellEditable(int row, int column) { // Table 수정 못하게 함
                return false;
            }
        }); // 바뀐 데이터의 값을 Table에 적용함
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("시작일:");
        jPanel1.add(jLabel1);

        jTextField1.setColumns(8);
        jPanel1.add(jTextField1);

        jLabel2.setText("     ");
        jPanel1.add(jLabel2);

        jLabel3.setText("종료일:");
        jPanel1.add(jLabel3);

        jTextField2.setColumns(8);
        jPanel1.add(jTextField2);

        ImageIcon icon = new ImageIcon("src/images/Search.png");
        Image img = icon.getImage().getScaledInstance(21, 21, Image.SCALE_SMOOTH);
        jButton1.setIcon(new ImageIcon(img));
        jButton1.setPreferredSize(new java.awt.Dimension(21, 21));
//        jButton1.setBorder(new BevelBorder(BevelBorder.RAISED));
        jButton1.setBorder(BorderFactory.createLineBorder(Color.green, 1)); // 버튼의 효과 추가
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data, c_name));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new EmpFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;

    public void updateData(EmpVO vo) {
        SqlSession ss = factory.openSession();
        int cnt = ss.update("emp.edit", vo);
        if(cnt > 0) {
            ss.commit();
            // JTable값을 갱신한다. 사용자가 JTable에서 더블클릭한
            // 행번호(index)를 알아야 한다.
//            jTable1.setValueAt(vo.getEname(), i, 1); // 이름
//            jTable1.setValueAt(vo.getHiredate(), i, 2); // 입사일
//            jTable1.setValueAt(vo.getSal(), i, 3); // 급여
            // 멤버변수 List의 내용도 변경해야 한다.
            list.set(i, vo);
            viewTable(list);
        } else
            ss.rollback();
        ss.close();
    }
    // End of variables declaration//GEN-END:variables
}
