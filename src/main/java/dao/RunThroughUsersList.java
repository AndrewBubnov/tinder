package dao;

import model.UserList;

public class RunThroughUsersList {
 int index = 0;
 UserList userList = new UserList();

  public int get() {
      if (index < userList.get().size() - 1) {
          index++;
      } else index = 0;
      return index;
  }
}
