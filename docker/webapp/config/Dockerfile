FROM centos:7
ENV container docker
RUN yum -y update; yum clean all
RUN yum -y install systemd; yum clean all; \
(cd /lib/systemd/system/sysinit.target.wants/; for i in *; do [ $i == systemd-tmpfiles-setup.service ] || rm -f $i; done); \
rm -f /lib/systemd/system/multi-user.target.wants/*;\
rm -f /etc/systemd/system/*.wants/*;\
rm -f /lib/systemd/system/local-fs.target.wants/*; \
rm -f /lib/systemd/system/sockets.target.wants/*udev*; \
rm -f /lib/systemd/system/sockets.target.wants/*initctl*; \
rm -f /lib/systemd/system/basic.target.wants/*;\
rm -f /lib/systemd/system/anaconda.target.wants/*;

RUN yum install java-11-openjdk-devel -y
RUN yum install net-tools -y
RUN yum install wget -y
RUN yum install nano -y
RUN yum install openssh-clients -y
RUN yum install openssh-server -y
RUN echo 'root:qwerqwer' | chpasswd

RUN useradd -m -U -d /opt/tomcat -s /bin/false tomcat

RUN cd /tmp
RUN wget http://archive.apache.org/dist/tomcat/tomcat-9/v9.0.8/bin/apache-tomcat-9.0.8.tar.gz
RUN tar -xf apache-tomcat-9.0.8.tar.gz
RUN mv apache-tomcat-9.0.8 /opt/tomcat/
RUN ln -s /opt/tomcat/apache-tomcat-9.0.8 /opt/tomcat/latest
RUN chown -R tomcat: /opt/tomcat
RUN sh -c 'chmod +x /opt/tomcat/latest/bin/*.sh'


COPY tomcat.service /etc/systemd/system/
COPY tomcat-users.xml /opt/tomcat/latest/conf/
COPY manager/META-INF/context.xml /opt/tomcat/latest/webapps/manager/META-INF/
COPY host-manager/META-INF/context.xml /opt/tomcat/latest/webapps/host-manager/META-INF/


EXPOSE 8080
EXPOSE 8080/tcp
EXPOSE 8080/udp
VOLUME /sys/fs/cgroup
CMD /usr/sbin/init

