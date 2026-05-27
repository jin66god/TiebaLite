package com.huanchengfly.tieba.post.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val MIGRATION_38_39 = Migration(38, 39) { db ->
    db.execSQL("CREATE TABLE IF NOT EXISTS `account_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `uid` TEXT NOT NULL, `name` TEXT NOT NULL, `bduss` TEXT NOT NULL, `tbs` TEXT NOT NULL, `portrait` TEXT NOT NULL, `stoken` TEXT NOT NULL, `cookie` TEXT NOT NULL, `nameshow` TEXT, `intro` TEXT, `sex` TEXT, `fansnum` TEXT, `postnum` TEXT, `threadnum` TEXT, `concernnum` TEXT, `tbage` TEXT, `age` TEXT, `birthdayshowstatus` TEXT, `birthdaytime` TEXT, `constellation` TEXT, `tiebauid` TEXT, `loadsuccess` INTEGER NOT NULL, `uuid` TEXT, `zid` TEXT)")
    db.execSQL("INSERT INTO `account_new` (`id`, `uid`, `name`, `bduss`, `tbs`, `portrait`, `stoken`, `cookie`, `nameshow`, `intro`, `sex`, `fansnum`, `postnum`, `threadnum`, `concernnum`, `tbage`, `age`, `birthdayshowstatus`, `birthdaytime`, `constellation`, `tiebauid`, `loadsuccess`, `uuid`, `zid`) SELECT `id`, IFNULL(`uid`, ''), IFNULL(`name`, ''), IFNULL(`bduss`, ''), IFNULL(`tbs`, ''), IFNULL(`portrait`, ''), IFNULL(`stoken`, ''), IFNULL(`cookie`, ''), `nameshow`, `intro`, `sex`, `fansnum`, `postnum`, `threadnum`, `concernnum`, `tbage`, `age`, `birthdayshowstatus`, `birthdaytime`, `constellation`, `tiebauid`, IFNULL(`loadsuccess`, 0), `uuid`, `zid` FROM `account`")
    db.execSQL("DROP TABLE IF EXISTS `account`")
    db.execSQL("ALTER TABLE `account_new` RENAME TO `account`")

    db.execSQL("CREATE TABLE IF NOT EXISTS `draft_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `hash` TEXT NOT NULL, `content` TEXT NOT NULL)")
    db.execSQL("INSERT INTO `draft_new` (`id`, `hash`, `content`) SELECT `id`, IFNULL(`hash`, ''), IFNULL(`content`, '') FROM `draft`")
    db.execSQL("DROP TABLE IF EXISTS `draft`")
    db.execSQL("ALTER TABLE `draft_new` RENAME TO `draft`")

    db.execSQL("CREATE TABLE IF NOT EXISTS `history_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `title` TEXT NOT NULL, `data` TEXT NOT NULL, `type` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `count` INTEGER NOT NULL, `extras` TEXT, `avatar` TEXT, `username` TEXT)")
    db.execSQL("INSERT INTO `history_new` (`id`, `title`, `data`, `type`, `timestamp`, `count`, `extras`, `avatar`, `username`) SELECT `id`, IFNULL(`title`, ''), IFNULL(`data`, ''), IFNULL(`type`, 0), IFNULL(`timestamp`, 0), IFNULL(`count`, 0), `extras`, `avatar`, `username` FROM `history`")
    db.execSQL("DROP TABLE IF EXISTS `history`")
    db.execSQL("ALTER TABLE `history_new` RENAME TO `history`")

    db.execSQL("CREATE TABLE IF NOT EXISTS `block_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `category` INTEGER NOT NULL, `type` INTEGER NOT NULL, `keywords` TEXT, `username` TEXT, `uid` TEXT, `isregex` INTEGER NOT NULL)")
    db.execSQL("INSERT INTO `block_new` (`id`, `category`, `type`, `keywords`, `username`, `uid`, `isregex`) SELECT `id`, IFNULL(`category`, 0), IFNULL(`type`, 0), `keywords`, `username`, `uid`, IFNULL(`isregex`, 0) FROM `block`")
    db.execSQL("DROP TABLE IF EXISTS `block`")
    db.execSQL("ALTER TABLE `block_new` RENAME TO `block`")

    db.execSQL("CREATE TABLE IF NOT EXISTS `topforum_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `forumid` TEXT NOT NULL)")
    db.execSQL("INSERT INTO `topforum_new` (`id`, `forumid`) SELECT `id`, IFNULL(`forumid`, '') FROM `topforum`")
    db.execSQL("DROP TABLE IF EXISTS `topforum`")
    db.execSQL("ALTER TABLE `topforum_new` RENAME TO `topforum`")

    db.execSQL("CREATE TABLE IF NOT EXISTS `searchhistory_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `content` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)")
    db.execSQL("INSERT INTO `searchhistory_new` (`id`, `content`, `timestamp`) SELECT `id`, IFNULL(`content`, ''), IFNULL(`timestamp`, 0) FROM `searchhistory`")
    db.execSQL("DROP TABLE IF EXISTS `searchhistory`")
    db.execSQL("ALTER TABLE `searchhistory_new` RENAME TO `searchhistory`")

    db.execSQL("CREATE TABLE IF NOT EXISTS `searchposthistory_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `content` TEXT NOT NULL, `forumname` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)")
    db.execSQL("INSERT INTO `searchposthistory_new` (`id`, `content`, `forumname`, `timestamp`) SELECT `id`, IFNULL(`content`, ''), IFNULL(`forumname`, ''), IFNULL(`timestamp`, 0) FROM `searchposthistory`")
    db.execSQL("DROP TABLE IF EXISTS `searchposthistory`")
    db.execSQL("ALTER TABLE `searchposthistory_new` RENAME TO `searchposthistory`")

    db.execSQL("INSERT OR REPLACE INTO `sqlite_sequence` (`name`, `seq`) SELECT 'account', MAX(`id`) FROM `account` UNION ALL SELECT 'draft', MAX(`id`) FROM `draft` UNION ALL SELECT 'history', MAX(`id`) FROM `history` UNION ALL SELECT 'block', MAX(`id`) FROM `block` UNION ALL SELECT 'topforum', MAX(`id`) FROM `topforum` UNION ALL SELECT 'searchhistory', MAX(`id`) FROM `searchhistory` UNION ALL SELECT 'searchposthistory', MAX(`id`) FROM `searchposthistory`")
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "tblite.db")
            .addMigrations(MIGRATION_38_39)
            .build()
    }

    @Provides
    fun provideAccountDao(db: AppDatabase) = db.accountDao()

    @Provides
    fun provideDraftDao(db: AppDatabase) = db.draftDao()

    @Provides
    fun provideHistoryDao(db: AppDatabase) = db.historyDao()

    @Provides
    fun provideBlockDao(db: AppDatabase) = db.blockDao()

    @Provides
    fun provideTopForumDao(db: AppDatabase) = db.topForumDao()

    @Provides
    fun provideSearchHistoryDao(db: AppDatabase) = db.searchHistoryDao()

    @Provides
    fun provideSearchPostHistoryDao(db: AppDatabase) = db.searchPostHistoryDao()
}
