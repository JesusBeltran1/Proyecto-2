import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import shiffman.box2d.*; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.joints.*; 
import org.jbox2d.collision.shapes.*; 
import org.jbox2d.collision.shapes.Shape; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.*; 
import org.jbox2d.dynamics.contacts.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Derriba_la_Torre extends PApplet {










Box2DProcessing box2d;
Roca roca;
Boundary boundary;
Boundary2 boundary2;
Stickman stickman;
Ladrillo1 ladrillo1;
Ladrillo2 ladrillo2;
Ladrillo3 ladrillo3;
Ladrillo4 ladrillo4;
Ladrillo5 ladrillo5;
Ladrillo7 ladrillo7;
Ladrillo8 ladrillo8;

PImage escenario_del_derribe;
PImage cuerpo_del_stickman;
boolean shot = false;
PImage pantalla_de_inicio;
int pantalla = 0;
PImage fin_del_juego;

public void setup() {
  
  box2d = new Box2DProcessing(this);
  box2d.createWorld();
  box2d.setGravity(0.0f,-10.0f);
  
  roca = new Roca();
  boundary = new Boundary();
  boundary2 = new Boundary2();
  stickman = new Stickman();
  ladrillo1 = new Ladrillo1();
  ladrillo2 = new Ladrillo2();
  ladrillo3 = new Ladrillo3();
  ladrillo4 = new Ladrillo4();
  ladrillo5 = new Ladrillo5();
  ladrillo7 = new Ladrillo7();
  ladrillo8 = new Ladrillo8();

  escenario_del_derribe = loadImage("Escenario del Derribe.png");
  cuerpo_del_stickman = loadImage("Cuerpo del Stickman.png");
  pantalla_de_inicio = loadImage("Pantalla de Inicio de Derriba la Torre.png");
  fin_del_juego = loadImage("Fin del Juego.png");
}

public void draw() {  
  box2d.step();
  if(pantalla == 0){
    image(pantalla_de_inicio,0,0,1000,335);
  }else if(pantalla == 1){
  image(escenario_del_derribe, 0, 0, 1000, 335);
  roca.display();
  boundary.display();
  boundary2.display();
  stickman.display();
  ladrillo1.display();
  ladrillo2.display();
  ladrillo3.display();
  ladrillo4.display();
  ladrillo5.display();
  ladrillo7.display();
  ladrillo8.display();
  image(cuerpo_del_stickman,3,190,60,60);    
  } else if(pantalla == 2){
    image(fin_del_juego, 0, 0, 1000, 335);
  }
}

public void mousePressed(){
  if (mouseButton == LEFT && pantalla==1) {
    stickman.move(new Vec2(mouseX,mouseY));
    roca.angular(0.5f);
  }
}
  
public void keyReleased() {
  if((key == 'a' || key == 'A') && pantalla==0){
    pantalla=1;
  } else if((key == 'b' || key == 'B') && pantalla==1){
    pantalla=2;
  } else if((key == 'c' || key == 'C') && pantalla==2){
    exit();
  }
}
  
public void beginContact(Contact cp) {

  Fixture f1 = cp.getFixtureA();
  Fixture f2 = cp.getFixtureB();

  Body b1 = f1.getBody();
  Body b2 = f2.getBody();

  Object o1 = b1.getUserData();
  Object o2 = b2.getUserData(); 
}

public void endContact(Contact cp) {
}
class Boundary { 
  Body body;
  int r = 16;
  boolean thrusting = false;

  Boundary() {
    makeBody(new Vec2(90,110),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
    Vec2[] vertices = new Vec2[4];
    vertices[0] = box2d.vectorPixelsToWorld(new Vec2(-100,height/3));
    vertices[1] = box2d.vectorPixelsToWorld(new Vec2(-100,height/2));
    vertices[2] = box2d.vectorPixelsToWorld(new Vec2(50,height/2));
    vertices[3] = box2d.vectorPixelsToWorld(new Vec2(50,height/3));
 
    PolygonShape ps = new PolygonShape();
    ps.set(vertices, vertices.length);

    BodyDef bd = new BodyDef();
    bd.type = BodyType.STATIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    body.createFixture(ps, 1.0f);
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }

  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    noStroke();
    strokeWeight(2);
    noFill();
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Boundary2 { 
  Body body;
  int r = 16;
  boolean thrusting = false;

  Boundary2() {
    makeBody(new Vec2(width/2,175),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
    Vec2[] vertices = new Vec2[4];
    vertices[0] = box2d.vectorPixelsToWorld(new Vec2(-500,height/3));
    vertices[1] = box2d.vectorPixelsToWorld(new Vec2(-500,height/2));
    vertices[2] = box2d.vectorPixelsToWorld(new Vec2(500,height/2));
    vertices[3] = box2d.vectorPixelsToWorld(new Vec2(500,height/3));
 
    PolygonShape ps = new PolygonShape();
    ps.set(vertices, vertices.length);

    BodyDef bd = new BodyDef();
    bd.type = BodyType.STATIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    body.createFixture(ps, 1.0f);
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }

  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    noStroke();
    strokeWeight(2);
    noFill();
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo1 { 
  Body body;
  float r = 16;
  
  Ladrillo1() {
    makeBody(new Vec2(540,240),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
 
    PolygonShape ps = new PolygonShape();
    float ladrillo_1W = box2d.scalarPixelsToWorld(6);
    float ladrillo_1H = box2d.scalarPixelsToWorld(50);
     
    ps.setAsBox(ladrillo_1W, ladrillo_1H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo2 { 
  Body body;
  float r = 16;  

  Ladrillo2() {
    makeBody(new Vec2(620,240),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
    PolygonShape ps = new PolygonShape();
    float ladrillo_2W = box2d.scalarPixelsToWorld(6);
    float ladrillo_2H = box2d.scalarPixelsToWorld(50);
     
    ps.setAsBox(ladrillo_2W, ladrillo_2H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo3 { 
  Body body;
  float r = 16;

  Ladrillo3() {
    makeBody(new Vec2(580,178),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
    
    PolygonShape ps = new PolygonShape();
    float ladrillo_3W = box2d.scalarPixelsToWorld(50);
    float ladrillo_3H = box2d.scalarPixelsToWorld(6);
     
    ps.setAsBox(ladrillo_3W, ladrillo_3H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo4 { 
  Body body;
  float r = 16;

  Ladrillo4() {
    makeBody(new Vec2(550,130),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
    PolygonShape ps = new PolygonShape();
    float ladrillo_4W = box2d.scalarPixelsToWorld(6);
    float ladrillo_4H = box2d.scalarPixelsToWorld(40);
     
    ps.setAsBox(ladrillo_4W, ladrillo_4H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo5 { 
  Body body;
  float r = 16;

  Ladrillo5() {
    makeBody(new Vec2(610,130),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
    
    PolygonShape ps = new PolygonShape();
    float ladrillo_5W = box2d.scalarPixelsToWorld(6);
    float ladrillo_5H = box2d.scalarPixelsToWorld(40);
     
    ps.setAsBox(ladrillo_5W, ladrillo_5H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo7 { 
  Body body;
  float r = 16;

  Ladrillo7() {
    makeBody(new Vec2(578,105),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
    PolygonShape ps = new PolygonShape();
    float ladrillo_7W = box2d.scalarPixelsToWorld(50);
    float ladrillo_7H = box2d.scalarPixelsToWorld(6);
     
    ps.setAsBox(ladrillo_7W, ladrillo_7H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Ladrillo8 { 
  Body body;
  float r = 16;
  
  Ladrillo8() {
    makeBody(new Vec2(580,80),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
    PolygonShape ps = new PolygonShape();
    float ladrillo_8W = box2d.scalarPixelsToWorld(10);
    float ladrillo_8H = box2d.scalarPixelsToWorld(20);
     
    ps.setAsBox(ladrillo_8W, ladrillo_8H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
        
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(0xff969696);
    strokeWeight(3);
    fill(0xffB73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
class Roca { 
  Body body;
  float r = 20;
  
  Roca() {
    makeBody(new Vec2(60,190),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
   public boolean contains(float x, float y) {
    Vec2 worldPoint = box2d.coordPixelsToWorld(x, y);
    Fixture f = body.getFixtureList();
    boolean inside = f.testPoint(worldPoint);
    return inside;
  }
  
  public void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
    PolygonShape ps = new PolygonShape();
    float rocaW = box2d.scalarPixelsToWorld(20);
    float rocaH = box2d.scalarPixelsToWorld(30);
     
    ps.setAsBox(rocaW, rocaH);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
    
    bd.angle = angle; 
    
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 2;
    fd.friction = 2;

    body.createFixture(fd);
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  public void angular(float angulo){
      body.setAngularVelocity(angulo);
    }
  
  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
 
    pushMatrix();
    stroke(0xff646464);
    strokeWeight(3);
    fill(0xff8E8E8E);
    ellipse(pos.x,pos.y, r, r);
    popMatrix();
  }
}
class Stickman { 
  Body body;
  int r = 16;
  Vec2 vel = new Vec2(10,0);
  boolean thrusting = false;

  Stickman() {
    makeBody(new Vec2(30,190));
    body.setUserData(this);
  } 
  
  public void makeBody(Vec2 position) {
    Vec2[] vertices = new Vec2[4];
    vertices[0] = box2d.vectorPixelsToWorld(new Vec2(r,0));
    vertices[1] = box2d.vectorPixelsToWorld(new Vec2(r,r/2));
    vertices[2] = box2d.vectorPixelsToWorld(new Vec2(-r,r/2));
    vertices[3] = box2d.vectorPixelsToWorld(new Vec2(-r,0));

    PolygonShape ps = new PolygonShape();
    ps.set(vertices, vertices.length);

    BodyDef bd = new BodyDef();
    bd.type = BodyType.KINEMATIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    body = box2d.createBody(bd);
    
    body.createFixture(ps, 1.0f);
  }
  
  public void move(Vec2 vel){
      body.setLinearVelocity(vel);
    }

  public void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    noStroke();
    strokeWeight(2);
    noFill();
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
  public void settings() {  size(1000, 335); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Derriba_la_Torre" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
