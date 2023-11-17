require 'rails_helper'

describe HelloWorld do
  it "should say 'Hello World' when we cal the say_hello method" do
    hw = HelloWorld.new
    message = hw.say_hello
    expect(message).to eq "Hello World!"
  end
end