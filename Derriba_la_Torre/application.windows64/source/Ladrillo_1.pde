class Ladrillo1 { 
  Body body;
  float r = 16;
  
  Ladrillo1() {
    makeBody(new Vec2(540,240),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
 
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
  
  void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(#969696);
    strokeWeight(3);
    fill(#B73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
