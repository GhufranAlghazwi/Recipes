package org.tuwaiq.recipes.util

import android.app.Activity
import android.content.res.Resources
import java.util.*

class LocalizationHelper {

    companion object {

        fun changeLanguage(activity: Activity, newCode: String): Unit {

            var locale = Locale(newCode);
            Locale.setDefault(locale);
            var resources = activity.resources;
            var config = resources.configuration;
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());

            var currentIntent = activity.intent
            activity.finish()
            activity.startActivity(currentIntent)

        }

    }

}