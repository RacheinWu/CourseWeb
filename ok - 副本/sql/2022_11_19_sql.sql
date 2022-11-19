create table attach
(
    id bigint auto_increment comment 'id'
        primary key,
    attachType varchar(40) null comment '附件类型(视频:video,课件:courseware,图片:img)',
    dataId bigint null comment '数据Id',
    fileName varchar(100) null comment '附件名称',
    relativePath varchar(200) null comment '附件Url',
    fileSize int null comment '附件大小',
    extName varchar(40) null comment '附件扩展名',
    state varchar(40) null comment '状态',
    downloadCount int default 0 null comment '下载次数',
    constraint attach_relativePath_uindex
        unique (relativePath)
)
    comment '附件';

create table banner
(
    id bigint auto_increment comment 'id'
        primary key,
    title varchar(40) null comment '标题',
    photoUrl varchar(200) null comment '图片Url',
    link varchar(200) null comment '链接地址',
    showFlag varchar(40) null comment '是否显示',
    orderNum int null comment '顺序',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间'
)
    comment '轮播图';

create table cases
(
    id bigint auto_increment comment 'id'
        primary key,
    titile varchar(40) null comment '标题',
    author varchar(40) null comment '作者',
    publisher varchar(40) null comment '发布人',
    content longtext null comment '文本内容',
    lastId bigint null comment '上一篇Id',
    lastTitle varchar(40) null comment '上一篇标题',
    nextId bigint null comment '下一篇Id',
    nextTitle varchar(40) null comment '下一篇标题',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间',
    watchingCount int default 0 null comment '浏览次数'
)
    comment '案例';

create table courseware
(
    id bigint auto_increment comment 'id'
        primary key,
    coursewareType varchar(40) null comment '类型',
    tile varchar(40) null comment '标题',
    author varchar(40) null comment '作者',
    coursewareUrl varchar(200) null comment '课件封面图URL',
    downloadFlag varchar(40) null comment '是否可下载',
    lastId bigint null comment '上一篇Id',
    lastTitle varchar(40) null comment '上一篇标题',
    nextId bigint null comment '下一篇Id',
    nextTitle varchar(40) null comment '下一篇标题',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间'
)
    comment '课件(其他附件在附件表中)';

create table experience
(
    id bigint auto_increment comment 'id'
        primary key,
    title varchar(40) null comment '标题',
    photoUrl varchar(200) null comment '图片Url',
    source varchar(100) null comment '来源',
    author varchar(40) null comment '作者',
    content longtext null comment '文本内容',
    lastId bigint null comment '上一篇Id',
    lastTitle varchar(40) null comment '上一篇标题',
    nextId bigint null comment '下一篇Id',
    nextTitle varchar(40) null comment '下一篇标题',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间',
    watchingCount int default 1 null comment '浏览量'
)
    comment '经验';

create table intro
(
    id int auto_increment
        primary key,
    title varchar(100) null,
    createTime datetime null,
    author varchar(20) null,
    content longtext null,
    type varchar(255) null,
    watchingCount int default 1 null comment '浏览量'
);

create table msgcontent
(
    id bigint auto_increment comment 'id'
        primary key,
    titleId bigint null comment '所属标题Id',
    title varchar(100) null comment '标题',
    msgType varchar(40) null comment '类型(发起:发起 回复:回复)',
    content varchar(500) null comment '留言内容',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间'
)
    comment '留言内容';

create table msgtitle
(
    id bigint auto_increment comment 'id'
        primary key,
    title varchar(100) null comment '标题',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间'
)
    comment '留言标题';

create table news
(
    id bigint auto_increment comment 'id'
        primary key,
    title varchar(40) null comment '标题',
    remark varchar(500) null comment '说明',
    photoUrl varchar(200) null comment '封面图片Url',
    content longtext null comment '文本内容',
    source varchar(100) null comment '来源',
    link varchar(200) null comment '链接地址',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间',
    watchingCount int default 1 null comment '浏览量'
)
    comment '新闻';

create table notice
(
    id bigint auto_increment comment 'id'
        primary key,
    title varchar(40) null comment '标题',
    publishOrg varchar(40) null comment '发布单位',
    content longtext null comment '文本内容',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间'
)
    comment '公告';

create table policy
(
    id bigint auto_increment comment 'id'
        primary key,
    title varchar(40) null comment '标题',
    remark varchar(500) null comment '说明',
    photoUrl varchar(200) null comment '封面图片Url',
    content longtext null comment '文本内容',
    source varchar(100) null comment '来源',
    link varchar(200) null comment '原文链接',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间'
)
    comment '政策';

create table practice
(
    id bigint auto_increment comment 'id'
        primary key,
    practiceName varchar(40) null comment '实践名称',
    photoUrl varchar(200) null comment '图片Url',
    startDate datetime null comment '开始时间',
    endDate datetime null comment '结束时间',
    teamName varchar(40) null comment '队伍名称',
    leader varchar(40) null comment '负责人',
    members varchar(200) null comment '队伍成员',
    intro longtext null comment '实践简介',
    remark longtext null comment '实践描述',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间',
    watchingCount int default 1 null comment '浏览量'
)
    comment '社会实践';

create table teacher
(
    id bigint auto_increment comment 'id'
        primary key,
    teacherType varchar(40) null comment '类型(名师队伍:famous,负责人:leader)',
    teacherName varchar(40) null comment '名称',
    title varchar(40) null comment '职称',
    post varchar(40) null comment '职务',
    email varchar(100) null comment 'email',
    photoUrl varchar(200) null comment '头像URL',
    content longtext null comment '文本内容',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间',
    honor varchar(40) null
)
    comment '教师';

create table test_code_gen
(
    id bigint auto_increment comment 'id'
        primary key,
    international varchar(100) null comment '国际',
    name varchar(100) null comment '姓名',
    age int null comment '年龄',
    birthday datetime null comment '生日',
    sex varchar(40) null comment '性别',
    hobby varchar(200) null comment '爱好',
    photoUrl varchar(200) null comment '头像',
    intro longtext null comment '简介',
    remark varchar(1000) null comment '备注',
    lifePhoto varchar(300) null comment '生活照',
    others varchar(300) null comment '其他资料'
)
    comment '测试代码生成';

create table users
(
    id bigint auto_increment comment 'id'
        primary key,
    userType varchar(40) null comment '类型(admin:admin,老师:teacher)',
    account varchar(40) null comment '账号/姓名',
    pwd varchar(40) null comment '密码',
    age int null comment '年龄',
    phone varchar(11) null comment '手机',
    email varchar(100) null comment '邮箱',
    nickName varchar(100) null comment '昵称',
    avatar varchar(200) null comment '头像Url',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间'
)
    comment '用户';

create table video
(
    id bigint auto_increment comment 'id'
        primary key,
    videoType varchar(40) null comment '类型',
    title varchar(40) null comment '标题',
    teacher varchar(40) null comment '授课老师',
    photoUrl varchar(200) null comment '图片Url',
    videoUrl varchar(200) null comment '视频封面图URL',
    remark longtext null comment '视频描述',
    lastId bigint null comment '上一篇Id',
    lastTitle varchar(40) null comment '上一篇标题',
    nextId bigint null comment '下一篇Id',
    nextTitle varchar(40) null comment '下一篇标题',
    state varchar(40) null comment '状态',
    createDate datetime null comment '创建时间',
    publishDate datetime null comment '发布时间',
    watchingCount int default 1 null comment '浏览量'
)
    comment '视频(其他附件)';

