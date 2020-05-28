package com.nakharin.pocdeeplink.shared.deeplink.di

import com.nakharin.pocdeeplink.shared.deeplink.DeeplinkCommand
import com.nakharin.pocdeeplink.shared.deeplink.DeeplinkMatcher
import com.nakharin.pocdeeplink.shared.deeplink.command.FoodDeeplinkCommand
import com.nakharin.pocdeeplink.shared.deeplink.command.MainDeeplinkCommand
import com.nakharin.pocdeeplink.shared.deeplink.processor.DefaultDeeplinkProcessor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DeeplinkModule {

    val module = module {

        // DeeplinkCommand
        single {
            FoodDeeplinkCommand(
                context = androidContext(),
                deeplinkMatcher = get(),
                navigationBuilder = get()
            )
        }

        single {
            MainDeeplinkCommand(
                context = androidContext(),
                deeplinkMatcher = get(),
                navigationBuilder = get()
            )
        }

        // DeeplinkProcessor
        single {
            DefaultDeeplinkProcessor(
                commands = provideDefaultCommands(get(), get())
            )
        }

        // DeeplinkMatcher
        single {
            DeeplinkMatcher()
        }
    }

    private fun provideDefaultCommands(
        mainDeeplinkCommand: MainDeeplinkCommand,
        foodDeeplinkCommand: FoodDeeplinkCommand
    ): Set<DeeplinkCommand> {
        return setOf(mainDeeplinkCommand, foodDeeplinkCommand)
    }
}
