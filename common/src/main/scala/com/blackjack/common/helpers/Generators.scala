package com.blackjack.common.helpers
import scala.util.Random

object Generators {
	val random = new Random()
	def correlationId: Int = random.nextInt()

}
