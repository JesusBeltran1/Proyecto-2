class Roca { 
  Body body;
  float r = 20;
  
  Roca() {
    makeBody(new Vec2(60,190),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
   boolean contains(float x, float y) {
    Vec2 worldPoint = box2d.coordPixelsToWorld(x, y);
    Fixture f = body.getFixtureList();
    boolean inside = f.testPoint(worldPoint);
    return inside;
  }
  
  void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
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
  
  void angular(float angulo){
      body.setAngularVelocity(angulo);
    }
  
  void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
 
    pushMatrix();
    stroke(#646464);
    strokeWeight(3);
    fill(#8E8E8E);
    ellipse(pos.x,pos.y, r, r);
    popMatrix();
  }
}
