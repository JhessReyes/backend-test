package stsa.kotlin_htmx.api.v1.utils

import stsa.kotlin_htmx.api.v1.responses.externalSource.AgentResponse
import stsa.kotlin_htmx.api.v1.responses.externalSource.CrateResponse
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

    fun agentsToXml(agents: List<AgentResponse>): String {
        val xml: String
        if (agents.isNotEmpty()) {
            xml = buildString {
                append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                append("<agents>")

                agents.forEach { a ->
                    append("<agent>")
                    append("<id>${a.id}</id>")
                    append("<name>${escapeHtmlTags(a.name)}</name>")
                    append("<description>${escapeHtmlTags(a.description)}</description>")
                    append("<image>${a.image}</image>")
                    append("<team>")
                    append("<id>${a.team.id}</id>")
                    append("<name>${a.team.name}</name>")
                    append("</team>")
                    append("</agent>")
                }

                append("</agents>")
            }
        } else xml = ""

        return xml
    }

    fun cratesToXml(crates: List<CrateResponse>): String {
        val xml: String
        if (crates.isNotEmpty()) {
            xml = buildString {
                append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                append("<crates>")

                crates.forEach { c ->
                    append("<crate>")
                    append("<id>${c.id}</id>")
                    append("<name>${escapeHtmlTags(c.name)}</name>")
                    if (!c.description.isNullOrBlank()) {
                        append("<description>${escapeHtmlTags(c.description)}</description>")
                    }
                    append("<image>${c.image}</image>")
                    append("</crate>")
                }

                append("</crates>")
            }
        } else xml = ""

        return xml
    }
}
