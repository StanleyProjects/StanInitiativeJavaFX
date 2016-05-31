package stan.initiative.telegram;

public class Bot
    extends Sender
    implements Consts
{
    public int chatIdMe;

    public Bot(int id, String token)
    {
    	super(BASE_URL + "/bot" + id + ":" + token);
    }

    public void setChatIdMe(int c)
    {
        chatIdMe = c;
    }

    public String sendFistStickerMe()
    {
        return sendStickerMe(STICKER_FIST_ID);
    }
    public String sendStickerMe(String stickerId)
    {
        return sendSticker(this.chatIdMe, stickerId);
    }
}