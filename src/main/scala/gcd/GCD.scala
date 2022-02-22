// See README.md for license details.

package gcd

import chisel3._
import chisel3.stage.ChiselStage

/**
  * Compute GCD using subtraction method.
  * Subtracts the smaller from the larger until register y is zero.
  * value in register x is then the GCD
  */
class GCD extends Module {
  val io = IO(new Bundle {
    val value1        = Input(UInt(16.W))
    val value2        = Input(UInt(16.W))
    val loadingValues = Input(Bool())
    val outputGCD     = Output(UInt(16.W))
    val outputValid   = Output(Bool())
  })
  when(io.loadingValues){
    io.outputGCD := 0.U
    io.outputValid := false.B
  }.otherwise{
    io.outputValid := true.B
  }
}

object emitFullAdder extends App{
  (new ChiselStage).emitVerilog((new GCD), Array("--target-dir", "generated"))
}