package methodConnection;

import cartManage.ProductCart;
import panels.AdminPanel;
import panels.UserPanel;
import productClass.ProductManage;
import panels.MainPanel;

public class MethodConnections {
    public void mainMethod(){
        ProductManage productManage = new ProductManage();
        ProductCart productCart = new ProductCart(productManage);
        UserPanel userPanel = new UserPanel(productCart);
        AdminPanel adminPanel = new AdminPanel(productManage);
        MainPanel mainPanel = new MainPanel(userPanel, adminPanel);
        mainPanel.showRegisterDisplay();
    }
}
