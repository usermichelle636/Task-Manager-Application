package com.example.taskmanagerapplication.model;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Task> __insertAdapterOfTask;

  private final EntityDeleteOrUpdateAdapter<Task> __deleteAdapterOfTask;

  private final EntityDeleteOrUpdateAdapter<Task> __updateAdapterOfTask;

  public TaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfTask = new EntityInsertAdapter<Task>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tasks` (`id`,`task_title`,`task_description`,`created_at`,`isCompleted`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.id);
        if (entity.taskTitle == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.taskTitle);
        }
        if (entity.taskDescription == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.taskDescription);
        }
        if (entity.createdAt == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.createdAt);
        }
        if (entity.isCompleted == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.isCompleted);
        }
      }
    };
    this.__deleteAdapterOfTask = new EntityDeleteOrUpdateAdapter<Task>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tasks` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfTask = new EntityDeleteOrUpdateAdapter<Task>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`task_title` = ?,`task_description` = ?,`created_at` = ?,`isCompleted` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Task entity) {
        statement.bindLong(1, entity.id);
        if (entity.taskTitle == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.taskTitle);
        }
        if (entity.taskDescription == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.taskDescription);
        }
        if (entity.createdAt == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.createdAt);
        }
        if (entity.isCompleted == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.isCompleted);
        }
        statement.bindLong(6, entity.id);
      }
    };
  }

  @Override
  public void insert(final Task task) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __insertAdapterOfTask.insert(_connection, task);
      return null;
    });
  }

  @Override
  public void delete(final Task task) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __deleteAdapterOfTask.handle(_connection, task);
      return null;
    });
  }

  @Override
  public void update(final Task task) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __updateAdapterOfTask.handle(_connection, task);
      return null;
    });
  }

  @Override
  public List<Task> getAllTasks() {
    final String _sql = "SELECT * FROM tasks";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTaskTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "task_title");
        final int _columnIndexOfTaskDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "task_description");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at");
        final int _columnIndexOfIsCompleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCompleted");
        final List<Task> _result = new ArrayList<Task>();
        while (_stmt.step()) {
          final Task _item;
          final String _tmpTaskTitle;
          if (_stmt.isNull(_columnIndexOfTaskTitle)) {
            _tmpTaskTitle = null;
          } else {
            _tmpTaskTitle = _stmt.getText(_columnIndexOfTaskTitle);
          }
          final String _tmpTaskDescription;
          if (_stmt.isNull(_columnIndexOfTaskDescription)) {
            _tmpTaskDescription = null;
          } else {
            _tmpTaskDescription = _stmt.getText(_columnIndexOfTaskDescription);
          }
          final String _tmpIsCompleted;
          if (_stmt.isNull(_columnIndexOfIsCompleted)) {
            _tmpIsCompleted = null;
          } else {
            _tmpIsCompleted = _stmt.getText(_columnIndexOfIsCompleted);
          }
          _item = new Task(_tmpTaskTitle,_tmpTaskDescription,_tmpIsCompleted);
          _item.id = (int) (_stmt.getLong(_columnIndexOfId));
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _item.createdAt = null;
          } else {
            _item.createdAt = _stmt.getLong(_columnIndexOfCreatedAt);
          }
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
