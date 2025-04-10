package stsa.kotlin_htmx.api.v1.utils

import stsa.kotlin_htmx.api.v1.responses.externalSource.SkinResponse

object XmlUtils {
    private fun escapeHtmlTags(input: String): String {
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;")
    }


    fun skinsToXml(skins: List<SkinResponse>): String {
        val xml: String
        if (skins.isNotEmpty()) {
            xml = buildString {
                append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                append("<skins>")
                skins.forEach { s ->
                    append("<skin>")
                    append("<id>${s.id}</id>")
                    append("<name>${escapeHtmlTags(s.name)}</name>")
                    append("<description>${escapeHtmlTags(s.description)}</description>")
                    append("<image>${s.image}</image>")
                    append("<team>")
                    append("<id>${s.team.id}</id>")
                    append("<name>${s.team.name}</name>")
                    append("</team>")
                    if (s.crates.isNotEmpty()) {
                        append("<crates>")
                        s.crates.forEach { c ->
                            append("<crate>")
                            append("<id>${c.id}</id>")
                            append("<name>${escapeHtmlTags(c.name)}</name>")
                            if (!c.description.isNullOrEmpty()) {
                                append("<description>${escapeHtmlTags(c.description)}</description>")
                            }
                            append("<image>${c.image}</image>")
                            append("</crate>")
                        }
                        append("</crates>")
                    }
                    append("</skin>")
                }
                append("</skins>")
            }
        } else xml = ""

        return xml
    }
}
