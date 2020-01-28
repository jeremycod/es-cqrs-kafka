package com.blackjack.statistics
import com.typesafe.config.ConfigFactory

object Config {
	private val config = ConfigFactory.load()

	object Api {
		private val apiConfig = config.getConfig("blackjack-app.api")

		val bindHost = apiConfig.getString("bind.host")
		val bindPort = apiConfig.getInt("bind.port")
	}
	object Game {
		private val gameConfig = config.getConfig("blackjack-app.game")

		val turnTimeoutSeconds = gameConfig.getInt("turnTimeoutSeconds")
	}

	object Events {
		private val eventsConfig = config.getConfig("blackjack-app.events")

		val exchangeName = eventsConfig.getString("exchangeName")
	}
}
