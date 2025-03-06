package stallitium;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class App extends JFrame{
    private JPanel panel;
    private JLabel name,id,type,tuika,log,nowpage;
    private JButton s1,s2,s3,s4,s5,next,back,add,reload,goPage,logClear;
    private JTextField namef,idf,typef,pageNumber;

    List<People> p = new ArrayList<>();
    int page = 0;
    Db db = new Db("app.db","test");
    public void run() {
        List<String> data = db.read(page);

        for (String s : data) {
            p.add(new People(s));
        }
        panel = new JPanel();
        panel.setLayout(null);
        try {
            s1 = new JButton(p.get(0).getName());
        } catch (IndexOutOfBoundsException e) {
            s1 = new JButton("");
        }
        s1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name.setText(p.get(0).getName());
                    id.setText(p.get(0).getId());
                    type.setText(p.get(0).getType());
                } catch (IndexOutOfBoundsException exception) {
                    name.setText("");
                    id.setText("");
                    type.setText("");
                }
            }
        });
        try {
            s2 = new JButton(p.get(1).getName());
        } catch (IndexOutOfBoundsException e) {
            s2 = new JButton("");
        }
        s2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name.setText(p.get(1).getName());
                    id.setText(p.get(1).getId());
                    type.setText(p.get(1).getType());
                } catch (IndexOutOfBoundsException exception) {
                    name.setText("");
                    id.setText("");
                    type.setText("");
                }
            }
        });
        try {
            s3 = new JButton(p.get(2).getName());
        } catch (IndexOutOfBoundsException e) {
            s3 = new JButton("");
        }
        s3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name.setText(p.get(2).getName());
                    id.setText(p.get(2).getId());
                    type.setText(p.get(2).getType());
                } catch (IndexOutOfBoundsException exception) {
                    name.setText("");
                    id.setText("");
                    type.setText("");
                }
            }
        });
        try {
            s4 = new JButton(p.get(3).getName());
        } catch (IndexOutOfBoundsException e) {
            s4 = new JButton("");
        }
        s4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name.setText(p.get(3).getName());
                    id.setText(p.get(3).getId());
                    type.setText(p.get(3).getType());
                } catch (IndexOutOfBoundsException exception) {
                    name.setText("");
                    id.setText("");
                    type.setText("");
                }
            }
        });
        try {
            s5 = new JButton(p.get(4).getName());
        } catch (IndexOutOfBoundsException e) {
            s5 = new JButton("");
        }
        s5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name.setText(p.get(4).getName());
                    id.setText(p.get(4).getId());
                    type.setText(p.get(4).getType());
                } catch (IndexOutOfBoundsException exception) {
                    name.setText("");
                    id.setText("");
                    type.setText("");
                }
            }
        });
        s1.setSize(100,100);
        s2.setSize(100,100);
        s3.setSize(100,100);
        s4.setSize(100,100);
        s5.setSize(100,100);
        s1.setLocation(0,0);
        s2.setLocation(0,100);
        s3.setLocation(0,200);
        s4.setLocation(0,300);
        s5.setLocation(0,400);
        panel.add(s1);
        panel.add(s2);
        panel.add(s3);
        panel.add(s4);
        panel.add(s5);
        name = new JLabel();
        id = new JLabel();
        type = new JLabel();
        name.setLocation(100,0);
        id.setLocation(100,20);
        type.setLocation(100,40);
        name.setSize(100,20);
        id.setSize(100,20);
        type.setSize(100,20);
        panel.add(name);
        panel.add(id);
        panel.add(type);

        tuika = new JLabel("追加");
        namef = new JTextField();
        idf = new JTextField();
        typef = new JTextField();
        add = new JButton("追加");
        log = new JLabel();
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!namef.getText().equals("") && !idf.getText().equals("") && !typef.getText().equals("")) {
                    if (db.write(namef.getText(),idf.getText(),typef.getText())) {
                        log.setText("更新完了");
                        load(0);
                        page = 0;
                    } else {
                        log.setText("追加完了");
                        load(0);
                        page = 0;
                    }
                } else {
                    log.setText("空欄があります");
                }
            }
        });
        tuika.setLocation(200,0);
        namef.setLocation(200,20);
        idf.setLocation(200,40);
        typef.setLocation(200,60);
        add.setLocation(200,80);
        log.setLocation(200,120);
        tuika.setSize(100,20);
        namef.setSize(100,20);
        idf.setSize(100,20);
        typef.setSize(100,20);
        add.setSize(100,40);
        log.setSize(100,20);
        panel.add(tuika);
        panel.add(namef);
        panel.add(idf);
        panel.add(typef);
        panel.add(add);
        panel.add(log);

        reload = new JButton("reload");
        reload.setLocation(200,140);
        reload.setSize(100,40);
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(page);
            }
        });
        panel.add(reload);

        next = new JButton("次のページ");
        back = new JButton("前のページ");
        next.setLocation(400,0);
        back.setLocation(300,0);
        next.setSize(100,100);
        back.setSize(100,100);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p.size() == 5) {
                    load(++page);
                } else {
                    log.setText("その先値なし");
                }

            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(--page);
            }
        });
        panel.add(next);
        panel.add(back);

        pageNumber = new JTextField(page);
        goPage = new JButton("Go");
        nowpage = new JLabel();
        pageNumber.setLocation(300,100);
        goPage.setLocation(300,120);
        nowpage.setLocation(400,100);
        pageNumber.setSize(100,20);
        goPage.setSize(100,40);
        nowpage.setSize(100,20);
        goPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(Integer.parseInt(pageNumber.getText()));
            }
        });
        panel.add(pageNumber);
        panel.add(goPage);
        panel.add(nowpage);

        logClear = new JButton("ログクリア");
        logClear.setLocation(200,180);
        logClear.setSize(100,40);
        logClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.setText("");
            }
        });
        panel.add(logClear);

        this.add(panel);
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void load(int page) {
        if (page < 0) {
            if (this.page < 0) {
                this.page = 0;
            }
            return;
        }
        List<String> data = db.read(page);
        p = new ArrayList<>();
        for (String s : data) {
            p.add(new People(s));
        }
        try {
            s1.setText(p.get(0).getName());
        } catch (IndexOutOfBoundsException e) {
            s1.setText("");
        }
        try {
            s2.setText(p.get(1).getName());
        } catch (IndexOutOfBoundsException e) {
            s2.setText("");
        }
        try {
            s3.setText(p.get(2).getName());
        } catch (IndexOutOfBoundsException e) {
            s3.setText("");
        }
        try {
            s4.setText(p.get(3).getName());
        } catch (IndexOutOfBoundsException e) {
            s4.setText("");
        }
        try {
            s5.setText(p.get(4).getName());
        } catch (IndexOutOfBoundsException e) {
            s5.setText("");
        }
        nowpage.setText(String.valueOf(page));
        pageNumber.setText(String.valueOf(page));
    }

    public static void main( String[] args ) {
        new App().run();
    }
}
