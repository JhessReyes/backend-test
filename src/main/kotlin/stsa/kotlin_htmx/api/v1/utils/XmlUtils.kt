package stsa.kotlin_htmx.api.v1.utils

import stsa.kotlin_htmx.api.v1.responses.externalSource.SkinResponse

object XmlUtils {
    fun skinsToXml(skins: List<SkinResponse>): String {
        val xml: String
        if (skins.isNotEmpty()) {
            xml = buildString {
                append("<skins>")
                skins.forEach { s ->
                    append("<skin>")
                    append("<id>${s.id}</id>")
                    append("<name>${s.name}</name>")
                    append("<description>${s.description}</description>")
                    append("<image>${s.image}</image>")
                    append("<team>")
                    append("<id>${s.team.id}</id>")
                    append("<name>${s.team.name}</name>")
                    append("</team>")
                    s.crates.forEach { c ->
                        append("<crate>")
                        append("<id>${c.id}</id>")
                        append("<name>${c.name}</name>")
                        if (!c.description.isNullOrEmpty()) {
                            append("<description>${c.description}</description>")
                        }
                        append("<image>${c.image}</image>")
                        append("</crate>")
                    }
                    append("</skin>")
                }
            }
        } else xml = ""

        return xml
    }
}
