import shiffman.box2d.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.*;

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

void setup() {
  size(1000, 335);
  box2d = new Box2DProcessing(this);
  box2d.createWorld();
  box2d.setGravity(0.0,-10.0);
  
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

void draw() {  
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

void mousePressed(){
  if (mouseButton == LEFT) {
    stickman.move(new Vec2(mouseX,mouseY));
    roca.angular(0.5);
  }
}
  
void keyReleased() {
  if(key == 'a' || key == 'A'){
    pantalla=1;
  } else if(key == 'b' || key == 'B'){
    pantalla=2;
  } else if(key == 'c' || key == 'C'){
    exit();
  }
}
  
void beginContact(Contact cp) {

  Fixture f1 = cp.getFixtureA();
  Fixture f2 = cp.getFixtureB();

  Body b1 = f1.getBody();
  Body b2 = f2.getBody();

  Object o1 = b1.getUserData();
  Object o2 = b2.getUserData(); 
}

void endContact(Contact cp) {
}
